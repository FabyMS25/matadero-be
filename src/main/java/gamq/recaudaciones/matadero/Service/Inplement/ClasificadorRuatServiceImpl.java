package gamq.recaudaciones.matadero.Service.Inplement;

import gamq.recaudaciones.matadero.Dto.ClasificadorRuatDto;
import gamq.recaudaciones.matadero.Dto.Mapper.CategoriaMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.ClasificadorRuatMapper;
import gamq.recaudaciones.matadero.Model.Categoria;
import gamq.recaudaciones.matadero.exception.NoResultException;
import gamq.recaudaciones.matadero.exception.NotFoundException;
import gamq.recaudaciones.matadero.exception.NullReferenceException;
import gamq.recaudaciones.matadero.Model.ClasificadorRuat;
import gamq.recaudaciones.matadero.Repository.ClasificadorRuatRepository;
import gamq.recaudaciones.matadero.Service.ClasificadorRuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gamq.recaudaciones.matadero.exception.enums.EntityType.*;
import static gamq.recaudaciones.matadero.exception.enums.EntityType.CLASFICADOR_RUAT;

@Service
@Transactional
public class ClasificadorRuatServiceImpl implements ClasificadorRuatService {

    @Autowired
    private ClasificadorRuatRepository clasificadorRuatRepository;

    @Override
    public ClasificadorRuatDto findByUuid(String uuid) {
        Optional<ClasificadorRuat> clasificadorOptional = clasificadorRuatRepository.findByUuid(uuid);
        if (clasificadorOptional.isPresent()) {
            return ClasificadorRuatMapper.toDto(clasificadorOptional.get());
        } else {
            throw new NotFoundException(CLASFICADOR_RUAT, uuid);
        }
    }

    @Override
    public ClasificadorRuatDto findByCodigo(String codigo) {
        Optional<ClasificadorRuat> clasificadorOptional = clasificadorRuatRepository.findByCodigo(codigo);
        if (clasificadorOptional.isPresent()) {
            return ClasificadorRuatMapper.toDto(clasificadorOptional.get());
        } else {
            throw new NotFoundException(CLASFICADOR_RUAT, codigo);
        }
    }

    @Override
    public List<ClasificadorRuatDto> obtenerClasificadores() {
        List<ClasificadorRuat> clasificadores = clasificadorRuatRepository.findAll();

        if (!clasificadores.isEmpty()) {
            return clasificadores.stream()
                    .map(ClasificadorRuatMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            throw new NoResultException(CLASFICADOR_RUAT);
        }
    }

    @Override
    public List<ClasificadorRuatDto> obtenerClasificadoresActivos() {
        List<ClasificadorRuat> clasificadores = clasificadorRuatRepository.findByActivoTrue();

        if (!clasificadores.isEmpty()) {
            return clasificadores.stream()
                    .map(ClasificadorRuatMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            throw new NoResultException(CLASFICADOR_RUAT);
        }
    }

//    @Override
//    public List<ClasificadorRuatDto> obtenerClasificadoresPorTipoReserva(String tipoReserva) {
//        List<ClasificadorRuat> clasificadores = clasificadorRuatRepository.findByTipoReserva(tipoReserva);
//
//        if (!clasificadores.isEmpty()) {
//            return clasificadores.stream()
//                    .map(ClasificadorRuatMapper::toDto)
//                    .collect(Collectors.toList());
//        } else {
//            throw new NoResultException(CLASIFICADOR_RUAT);
//        }
//    }

    @Override
    @Transactional
    public ClasificadorRuatDto crearClasificador(ClasificadorRuatDto clasificadorDto) {
        if (clasificadorDto == null) {
            throw new NullReferenceException(CLASFICADOR_RUAT);
        }

        if (clasificadorRuatRepository.findByCodigo(clasificadorDto.getCodigo()).isPresent()) {
            throw new RuntimeException(
                    "Ya existe un clasificador con el código: " + clasificadorDto.getCodigo()
            );
        }

        // 1️⃣ Mapper SIN categorías
        ClasificadorRuat clasificador = ClasificadorRuatMapper.toEntity(clasificadorDto);

        // 2️⃣ Asociar categorías correctamente
        if (clasificadorDto.getCategorias() != null) {
            clasificadorDto.getCategorias().forEach(catDto -> {
                Categoria categoria = CategoriaMapper.toEntity(catDto);
                clasificador.addCategoria(categoria); // 🔑 sincroniza ambos lados
            });
        }

        // 3️⃣ Guardar
        ClasificadorRuat clasificadorGuardado =
                clasificadorRuatRepository.save(clasificador);

        // 4️⃣ Retornar DTO
        return ClasificadorRuatMapper.toDto(clasificadorGuardado);

    }

    @Override
    @Transactional
    public ClasificadorRuatDto actualizarClasificador(ClasificadorRuatDto clasificadorDto) {
        if (clasificadorDto == null) {
            throw new NullReferenceException(CLASFICADOR_RUAT);
        }

        ClasificadorRuat clasificadorExistente = clasificadorRuatRepository.findByUuid(clasificadorDto.getUuid())
                .orElseThrow(() -> new NotFoundException(CLASFICADOR_RUAT, clasificadorDto.getUuid()));

        // Update clasificador information
        ClasificadorRuatMapper.updateEntityFromDto(clasificadorDto, clasificadorExistente);

        ClasificadorRuat clasificadorActualizado = clasificadorRuatRepository.save(clasificadorExistente);
        return ClasificadorRuatMapper.toDto(clasificadorActualizado);
    }

    @Override
    @Transactional
    public String eliminarClasificador(String uuid) {
        try {
            ClasificadorRuat clasificador = clasificadorRuatRepository.findByUuid(uuid)
                    .orElseThrow(() -> new NotFoundException(CLASFICADOR_RUAT, uuid));

            // Check if clasificador is used in any tarifa
            if (!clasificador.getCategorias().isEmpty()) {
                throw new RuntimeException("No se puede eliminar el clasificador porque está siendo utilizado en tarifas existentes.");
            }

            // Soft delete
            clasificador.setEstado(true);
            clasificadorRuatRepository.save(clasificador);
            return "Desactivación exitosa del clasificador RUAT";
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar desactivar el clasificador RUAT.", e);
        }
    }

    @Override
    @Transactional
    public String eliminarClasificadorPermanente(String uuid) {
        ClasificadorRuat clasificador = clasificadorRuatRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(CLASFICADOR_RUAT, uuid));

        // Check if clasificador is used in any tarifa
        if (!clasificador.getCategorias().isEmpty()) {
            throw new RuntimeException("No se puede eliminar permanentemente el clasificador porque está siendo utilizado en tarifas existentes.");
        }

        clasificadorRuatRepository.delete(clasificador);
        return "Eliminación permanente exitosa del clasificador RUAT";
    }
}