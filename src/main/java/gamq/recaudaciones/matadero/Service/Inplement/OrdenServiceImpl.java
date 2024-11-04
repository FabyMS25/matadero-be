package gamq.recaudaciones.matadero.Service.Inplement;

import gamq.recaudaciones.matadero.Dto.Mapper.OrdenMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.OrdenMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.OrdenMapper;
import gamq.recaudaciones.matadero.Dto.Mapper.OrdenMapper;
import gamq.recaudaciones.matadero.Dto.OrdenDto;
import gamq.recaudaciones.matadero.Model.*;
import gamq.recaudaciones.matadero.Model.Orden;
import gamq.recaudaciones.matadero.Model.Orden;
import gamq.recaudaciones.matadero.Repository.CategoriaRepository;
import gamq.recaudaciones.matadero.Repository.ContribuyenteRepository;
import gamq.recaudaciones.matadero.Repository.OrdenRepository;
import gamq.recaudaciones.matadero.Service.OrdenService;
import gamq.recaudaciones.matadero.exception.NoResultException;
import gamq.recaudaciones.matadero.exception.NotFoundException;
import gamq.recaudaciones.matadero.exception.NullReferenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gamq.recaudaciones.matadero.exception.enums.EntityType.*;

@Component
public class OrdenServiceImpl implements OrdenService {
   @Autowired
    OrdenRepository ordenRepository;
   @Autowired
    ContribuyenteRepository contribuyenteRepository;
   @Autowired
    CategoriaRepository categoriaRepository;
    
   public  OrdenDto findByUuid(String uuid){
        Optional<Orden> OrdenOptional = ordenRepository.findByUuid(uuid);
        if (OrdenOptional.isPresent()) {
            return OrdenMapper.toDto(OrdenOptional.get());
        } else {
            throw new NotFoundException(ORDEN, uuid);
        }
    }
    public List<OrdenDto> obtenerOrdenes(){
        List<Orden> Ordens = ordenRepository.findAll();

        if (!Ordens.isEmpty()) {
            return Ordens.stream().map(sol-> {
                return OrdenMapper.toDto(sol);
            }).collect(Collectors.toList());
        } else {
            throw new NoResultException(ORDEN);
        }
    }
    public OrdenDto crearOrden(OrdenDto ordenDto){
        if (ordenDto != null) {

            Orden newOrden = OrdenMapper.toEntity(ordenDto);
            Contribuyente contri=getContri(ordenDto.getContribuyenteDto().getUuid());
            newOrden.setContribuyente(contri);

            return OrdenMapper.toDto(ordenRepository.save(newOrden));
        } else {
            throw new NullReferenceException(ORDEN);
        }


    }
    public OrdenDto actualizarOrden(OrdenDto ordenDto){

        if (ordenDto != null) {

            Optional<Orden> OrdenOptional = ordenRepository.findByUuid(ordenDto.getUuid());

            if (OrdenOptional.isPresent()) {
                Orden ordenModificado = OrdenMapper.toEntity(ordenDto);
                ordenModificado.setIdSolictud(OrdenOptional.get().getIdSolictud());
                Contribuyente contri=getContri(ordenDto.getContribuyenteDto().getUuid());
                Categoria cate= getCategoria(ordenDto.getCategoriaDto().getUuid());
                ordenModificado.setContribuyente(contri);
                ordenModificado.setCategoria(cate);

                return OrdenMapper.toDto(ordenRepository.save(ordenModificado));
            } else {
                throw new NotFoundException(ORDEN, ordenDto.getUuid());
            }
        } else {
            throw new NullReferenceException(SOLICTUD);
        }


    }
    private Contribuyente getContri(String uuid) {
        return contribuyenteRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(CONTRIBUYENTE, uuid));
    }

    private Categoria getCategoria(String uuid) {
        return categoriaRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(CATEGORIA, uuid));
    }


}
