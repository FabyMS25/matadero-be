package gamq.recaudaciones.matadero.Service.Inplement;

import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Dto.Mapper.CategoriaMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.CategoriaMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.CategoriaMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.CategoriaMapper;
import gamq.recaudaciones.matadero.Model.*;
import gamq.recaudaciones.matadero.Model.Categoria;
import gamq.recaudaciones.matadero.Model.Categoria;
import gamq.recaudaciones.matadero.Model.Categoria;
import gamq.recaudaciones.matadero.Repository.CategoriaRepository;
import gamq.recaudaciones.matadero.Repository.ClasificadorRuatRepository;
import gamq.recaudaciones.matadero.Service.CategoriaService;
import gamq.recaudaciones.matadero.exception.NoResultException;
import gamq.recaudaciones.matadero.exception.NotFoundException;
import gamq.recaudaciones.matadero.exception.NullReferenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gamq.recaudaciones.matadero.exception.enums.EntityType.*;

@Component
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ClasificadorRuatRepository clasificadorRuatRepository;
   public CategoriaDto findByUuid(String uuid){
       Optional<Categoria> CategoriaOptional = categoriaRepository.findByUuid(uuid);
       if (CategoriaOptional.isPresent()) {
           return CategoriaMapper.toDto(CategoriaOptional.get());
       } else {
           throw new NotFoundException(CATEGORIA, uuid);
       }
   }
   
   public List<CategoriaDto> obtenerCategorias(){
       List<Categoria> Categorias = categoriaRepository.findAll();

       if (!Categorias.isEmpty()) {
           return Categorias.stream().map(cat-> {
               CategoriaDto catdto=CategoriaMapper.toDto(cat);
               catdto.setClasificadorUuid(cat.getClasificadorRuat().getUuid());
               return  catdto;
           }).collect(Collectors.toList());
       } else {
           throw new NoResultException(CATEGORIA);
       }
   }
    @Override
    @Transactional
    public CategoriaDto crearCategoria(CategoriaDto dto) {

        if (dto == null) {
            throw new NullReferenceException(CATEGORIA);
        }

        // 1️⃣ Buscar clasificador
        ClasificadorRuat clasificador = clasificadorRuatRepository
                .findByUuid(dto.getClasificadorUuid())
                .orElseThrow(() ->
                        new NotFoundException(
                                CLASFICADOR_RUAT,
                                dto.getClasificadorUuid()
                        )
                );

        // 2️⃣ Mapear categoría
        Categoria categoria = CategoriaMapper.toEntity(dto);

        // 3️⃣ Asociar correctamente
        clasificador.addCategoria(categoria);

        // 4️⃣ Guardar
        Categoria guardada = categoriaRepository.save(categoria);

        return CategoriaMapper.toDto(guardada);
    }

    @Override
    @Transactional
    public CategoriaDto actualizarCategoria(CategoriaDto dto) {

        if (dto == null) {
            throw new NullReferenceException(CATEGORIA);
        }

        Categoria categoria = categoriaRepository
                .findByUuid(dto.getUuid())
                .orElseThrow(() ->
                        new NotFoundException(CATEGORIA, dto.getUuid())
                );

        // 1️⃣ Actualizar SOLO campos simples
        categoria.setTipo(dto.getTipo());
        categoria.setPrecio(dto.getPrecio());
        categoria.setEstado(dto.isEstado());

        // 2️⃣ Cambiar clasificador SOLO si viene en el DTO
        if (dto.getClasificadorUuid() != null) {
            ClasificadorRuat nuevoClasificador =
                    clasificadorRuatRepository
                            .findByUuid(dto.getClasificadorUuid())
                            .orElseThrow(() ->
                                    new NotFoundException(
                                            CLASFICADOR_RUAT,
                                            dto.getClasificadorUuid()
                                    )
                            );

            categoria.setClasificadorRuat(nuevoClasificador);
        }

        // 3️⃣ Guardar
        return CategoriaMapper.toDto(
                categoriaRepository.save(categoria)
        );
    }

    @Transactional
    @Override
    public String delete(String uuid) {
        Optional<Categoria> found = categoriaRepository.findByUuid(uuid);
        if (found.isPresent()) {
            Categoria categoria = found.get();
            categoriaRepository.delete(categoria);

            return "Eliminacion exitosa";
        } else {
            throw new NotFoundException(CATEGORIA, uuid);
        }
    }
}
