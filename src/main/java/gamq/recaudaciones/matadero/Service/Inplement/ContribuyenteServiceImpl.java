package gamq.recaudaciones.matadero.Service.Inplement;

import gamq.recaudaciones.matadero.Dto.ContribuyenteDto;
import gamq.recaudaciones.matadero.Dto.Mapper.ContribuyenteMapper;

import gamq.recaudaciones.matadero.Model.Contribuyente;

import gamq.recaudaciones.matadero.Repository.ContribuyenteRepository;
import gamq.recaudaciones.matadero.Service.ContribuyenteService;
import gamq.recaudaciones.matadero.exception.NoResultException;
import gamq.recaudaciones.matadero.exception.NotFoundException;
import gamq.recaudaciones.matadero.exception.NullReferenceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gamq.recaudaciones.matadero.exception.enums.EntityType.*;

public class ContribuyenteServiceImpl implements ContribuyenteService {

    @Autowired
    ContribuyenteRepository contribuyenteRepository;
    
    
    public ContribuyenteDto findByUuid(String uuid){
        Optional<Contribuyente> ContribuyenteOptional = contribuyenteRepository.findByUuid(uuid);
        if (ContribuyenteOptional.isPresent()) {
            return ContribuyenteMapper.toDto(ContribuyenteOptional.get());
        } else {
            throw new NotFoundException(CONTRIBUYENTE, uuid);
        }
    }
    public List<ContribuyenteDto> obtenerContribuyentees(){
        List<Contribuyente> Contribuyentes = contribuyenteRepository.findAll();

        if (!Contribuyentes.isEmpty()) {
            return Contribuyentes.stream().map(cont-> {
                return ContribuyenteMapper.toDto(cont);
            }).collect(Collectors.toList());
        } else {
            throw new NoResultException(CONTRIBUYENTE);
        }
    }
    public ContribuyenteDto crearContribuyente(ContribuyenteDto ContribuyenteDto){
        if (ContribuyenteDto != null) {

            Contribuyente newContribuyente = ContribuyenteMapper.toEntity(ContribuyenteDto);
           
            return ContribuyenteMapper.toDto(contribuyenteRepository.save(newContribuyente));
        } else {
            throw new NullReferenceException(CONTRIBUYENTE);
        }

    }
    public ContribuyenteDto actualizarContribuyente(ContribuyenteDto ContribuyenteDto){

        if (ContribuyenteDto != null) {

            Optional<Contribuyente> ContribuyenteOptional = contribuyenteRepository.findByUuid(ContribuyenteDto.getUuid());

            if (ContribuyenteOptional.isPresent()) {
                Contribuyente ContribuyenteModificado = ContribuyenteMapper.toEntity(ContribuyenteDto);
                ContribuyenteModificado.setIdContribuyente(ContribuyenteOptional.get().getIdContribuyente());

                return ContribuyenteMapper.toDto(contribuyenteRepository.save(ContribuyenteModificado));
            } else {
                throw new NotFoundException(CONTRIBUYENTE, ContribuyenteDto.getUuid());
            }
        } else {
            throw new NullReferenceException(SOLICTUD);
        }

    }

}
