package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.ContribuyenteDto;
import gamq.recaudaciones.matadero.Model.Contribuyente;

public class ContribuyenteMapper {
    public static ContribuyenteDto toDto(Contribuyente contribuyente){
        ContribuyenteDto contribuyenteDto=new ContribuyenteDto();
        contribuyenteDto.setUuid(contribuyente.getUuid());
        contribuyenteDto.setNombre(contribuyente.getNombre());
        contribuyenteDto.setApPaterno(contribuyente.getApPaterno());
        contribuyenteDto.setApMaterno(contribuyente.getApMaterno());
        contribuyenteDto.setCiExpedido(contribuyente.getCiExpedido());
        contribuyenteDto.setNroCarnet(contribuyente.getNroCarnet());
        contribuyenteDto.setNroNit(contribuyente.getNroNit());
        contribuyenteDto.setSexo(contribuyente.getSexo());
        contribuyenteDto.setFechaNac(contribuyente.getFechaNac());
        contribuyenteDto.setEstadoCivil(contribuyente.getEstadoCivil());
        contribuyenteDto.setDireccion(contribuyente.getDireccion());
        contribuyenteDto.setTipoDocumento(contribuyente.getTipoDocumento());

        return contribuyenteDto;
    }
    public static Contribuyente toEntity(ContribuyenteDto contribuyenteDto){
        Contribuyente contribuyente=new Contribuyente();
        contribuyente.setUuid(contribuyenteDto.getUuid());
        contribuyente.setNombre(contribuyenteDto.getNombre());
        contribuyente.setApPaterno(contribuyente.getApPaterno());
        contribuyente.setApMaterno(contribuyenteDto.getApMaterno());
        contribuyente.setCiExpedido(contribuyenteDto.getCiExpedido());
        contribuyente.setNroCarnet(contribuyente.getNroCarnet());
        contribuyente.setNroNit(contribuyenteDto.getNroNit());
        contribuyente.setSexo(contribuyenteDto.getSexo());
        contribuyente.setFechaNac(contribuyenteDto.getFechaNac());
        contribuyente.setEstadoCivil(contribuyenteDto.getEstadoCivil());
        contribuyente.setDireccion(contribuyente.getDireccion());
        contribuyente.setTipoDocumento(contribuyente.getTipoDocumento());


        return contribuyente;
    }
}
