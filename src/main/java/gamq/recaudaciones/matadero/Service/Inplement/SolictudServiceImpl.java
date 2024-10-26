package gamq.recaudaciones.matadero.Service.Inplement;

import gamq.recaudaciones.matadero.Dto.Mapper.SolicitudMapper;
import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import gamq.recaudaciones.matadero.Model.Contribuyente;
import gamq.recaudaciones.matadero.Model.Solicitud;
import gamq.recaudaciones.matadero.Repository.ContribuyenteRepository;
import gamq.recaudaciones.matadero.Repository.SolicitudRepository;
import gamq.recaudaciones.matadero.Service.SolicitudService;
import gamq.recaudaciones.matadero.exception.NoResultException;
import gamq.recaudaciones.matadero.exception.NotFoundException;
import gamq.recaudaciones.matadero.exception.NullReferenceException;
import gamq.recaudaciones.matadero.exception.enums.EntityType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gamq.recaudaciones.matadero.exception.enums.EntityType.CONTRIBUYENTE;
import static gamq.recaudaciones.matadero.exception.enums.EntityType.SOLICTUD;

public class SolictudServiceImpl implements SolicitudService {

    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    ContribuyenteRepository contribuyenteRepository;

    public SolicitudDto findByUuid(String uuid){
        Optional<Solicitud> SolicitudOptional = solicitudRepository.findByUuid(uuid);
        if (SolicitudOptional.isPresent()) {
            return SolicitudMapper.toDto(SolicitudOptional.get());
        } else {
            throw new NotFoundException(SOLICTUD, uuid);
        }

    }
    public List<SolicitudDto> obtenerSolicitudes(){
        List<Solicitud> solicituds = solicitudRepository.findAll();

        if (!solicituds.isEmpty()) {
            return solicituds.stream().map(sol-> {
                return SolicitudMapper.toDto(sol);
            }).collect(Collectors.toList());
        } else {
            throw new NoResultException(SOLICTUD);
        }
    }
    public SolicitudDto crearSolicitud(SolicitudDto solicitudDto){
        if (solicitudDto != null) {

            Solicitud newSolicitud = SolicitudMapper.toEntity(solicitudDto);
            Contribuyente contri=getContri(solicitudDto.getContribuyenteDto().getUuid());
            newSolicitud.setContribuyente(contri);

            return SolicitudMapper.toDto(solicitudRepository.save(newSolicitud));
        } else {
            throw new NullReferenceException(SOLICTUD);
        }


    }
    public SolicitudDto actualizarSolicitud(SolicitudDto solicitudDto){
        if (solicitudDto != null) {

            Optional<Solicitud> SolicitudOptional = solicitudRepository.findByUuid(solicitudDto.getUuid());

            if (SolicitudOptional.isPresent()) {
                Solicitud solModificado = SolicitudMapper.toEntity(solicitudDto);
                solModificado.setIdSolictud(SolicitudOptional.get().getIdSolictud());
                Contribuyente contri=getContri(solicitudDto.getContribuyenteDto().getUuid());
                solModificado.setContribuyente(contri);

                return SolicitudMapper.toDto(solicitudRepository.save(solModificado));
            } else {
                throw new NotFoundException(SOLICTUD, solicitudDto.getUuid());
            }
        } else {
            throw new NullReferenceException(SOLICTUD);
        }

    }
    private Contribuyente getContri(String uuid) {
        return contribuyenteRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(CONTRIBUYENTE, uuid));
    }




}
