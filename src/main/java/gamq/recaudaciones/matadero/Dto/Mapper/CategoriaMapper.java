package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Model.Categoria;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria){
        CategoriaDto categoriaDto= new CategoriaDto();
        categoriaDto.setUuid(categoria.getUuid());
        categoriaDto.setTipo(categoria.getTipo());
        categoriaDto.setPrecio(categoria.getPrecio());
        categoriaDto.setEstado(categoria.isEstado());


        return categoriaDto;
    }


    public static Categoria toEntity(CategoriaDto categoriaDto){
        Categoria categoria=new Categoria();
        categoria.setUuid(categoriaDto.getUuid());
        categoria.setTipo(categoriaDto.getTipo());
        categoria.setPrecio(categoriaDto.getPrecio());
        categoria.setEstado(categoriaDto.isEstado());

        return categoria;
    }
}
