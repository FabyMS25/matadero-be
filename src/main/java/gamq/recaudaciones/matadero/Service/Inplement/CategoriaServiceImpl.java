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
           return Categorias.stream().map(cont-> {
               return CategoriaMapper.toDto(cont);
           }).collect(Collectors.toList());
       } else {
           throw new NoResultException(CATEGORIA);
       }
   }
   public  CategoriaDto crearCategoria(CategoriaDto CategoriaDto){
        if (CategoriaDto != null) {

            Categoria newCategoria = CategoriaMapper.toEntity(CategoriaDto);

            return CategoriaMapper.toDto(categoriaRepository.save(newCategoria));
        } else {
            throw new NullReferenceException(CATEGORIA);
        }
    }
   public CategoriaDto actualizarCategoria(CategoriaDto CategoriaDto){

       if (CategoriaDto != null) {

           Optional<Categoria> CategoriaOptional = categoriaRepository.findByUuid(CategoriaDto.getUuid());

           if (CategoriaOptional.isPresent()) {
               Categoria CategoriaModificado = CategoriaMapper.toEntity(CategoriaDto);
               CategoriaModificado.setIdCategoria(CategoriaOptional.get().getIdCategoria());

               return CategoriaMapper.toDto(categoriaRepository.save(CategoriaModificado));
           } else {
               throw new NotFoundException(CATEGORIA, CategoriaDto.getUuid());
           }
       } else {
           throw new NullReferenceException(CATEGORIA);
       }
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
