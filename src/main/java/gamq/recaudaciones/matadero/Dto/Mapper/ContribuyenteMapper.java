package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.ContribuyenteDto;
import gamq.recaudaciones.matadero.Model.Contribuyente;

public class ContribuyenteMapper {
    public static ContribuyenteDto toDto(Contribuyente contribuyente){
        ContribuyenteDto contribuyenteDto=new ContribuyenteDto();
        contribuyenteDto.setUuid(contribuyente.getUuid());
        contribuyenteDto.setTipoContribuyente(contribuyente.getTipoContribuyente());
        contribuyenteDto.setCodigoContribuyente(contribuyente.getCodigoContribuyente());
        contribuyenteDto.setTipoDocumento(contribuyente.getTipoDocumento());
        contribuyenteDto.setNumeroDocumento(contribuyente.getNumeroDocumento());
        contribuyenteDto.setExpedido(contribuyente.getExpedido());
        contribuyenteDto.setNombre(contribuyente.getNombre());
        contribuyenteDto.setPrimerApellido(contribuyente.getPrimerApellido());
        contribuyenteDto.setSegundoApellido(contribuyente.getSegundoApellido());
        contribuyenteDto.setGenero(contribuyente.getGenero());
        contribuyenteDto.setFechaNacimiento(contribuyente.getFechaNacimiento());
        contribuyenteDto.setEstadoCivil(contribuyente.getEstadoCivil());
        contribuyenteDto.setNroNit(contribuyente.getNroNit());
        contribuyenteDto.setCelular(contribuyente.getCelular());
        contribuyenteDto.setCorreo(contribuyente.getCorreo());
        contribuyenteDto.setDireccion(contribuyente.getDireccion());
        String fullName = contribuyente.getNombre() + " "
                + contribuyente.getPrimerApellido()
                + (contribuyente.getSegundoApellido().isEmpty() ? "" : " " + contribuyente.getSegundoApellido());
        contribuyenteDto.setFullName(fullName.trim());

        return contribuyenteDto;
    }
    public static Contribuyente toEntity(ContribuyenteDto contribuyenteDto){
        Contribuyente contribuyente=new Contribuyente();
        contribuyente.setUuid(contribuyenteDto.getUuid());
        contribuyente.setTipoContribuyente(contribuyenteDto.getTipoContribuyente());
        contribuyente.setCodigoContribuyente(contribuyenteDto.getCodigoContribuyente());
        contribuyente.setTipoDocumento(contribuyenteDto.getTipoDocumento());
        contribuyente.setNumeroDocumento(contribuyenteDto.getNumeroDocumento());
        contribuyente.setExpedido(contribuyenteDto.getExpedido());
        contribuyente.setNombre(contribuyenteDto.getNombre());
        contribuyente.setPrimerApellido(contribuyenteDto.getPrimerApellido());
        contribuyente.setSegundoApellido(contribuyenteDto.getSegundoApellido());
        contribuyente.setGenero(contribuyenteDto.getGenero());
        contribuyente.setFechaNacimiento(contribuyenteDto.getFechaNacimiento());
        contribuyente.setEstadoCivil(contribuyenteDto.getEstadoCivil());
        contribuyente.setNroNit(contribuyenteDto.getNroNit());
        contribuyente.setCelular(contribuyenteDto.getCelular());
        contribuyente.setCorreo(contribuyenteDto.getCorreo());
        contribuyente.setDireccion(contribuyenteDto.getDireccion());
        String fullName = contribuyenteDto.getNombre() + " "
                + contribuyenteDto.getPrimerApellido()
                + (contribuyenteDto.getSegundoApellido().isEmpty() ? "" : " " + contribuyenteDto.getSegundoApellido());
        contribuyente.setFullName(fullName.trim());

        return contribuyente;
    }
}
