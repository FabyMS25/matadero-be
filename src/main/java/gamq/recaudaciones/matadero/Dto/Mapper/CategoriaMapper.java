package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Model.Categoria;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria){
        CategoriaDto categoriaDto= new CategoriaDto();
        categoriaDto.setUuid(categoria.getUuid());
        categoriaDto.setTipo(categoria.getTipo());
        categoriaDto.setPrecio(categoriaDto.getPrecio());


        return categoriaDto;
    }


    public static Categoria toEntity(CategoriaDto categoriaDto){
        Categoria categoria=new Categoria();
        categoria.setUuid(categoriaDto.getUuid());
        categoria.setTipo(categoria.getTipo());
        categoria.setPrecio(categoriaDto.getPrecio());


        return categoria;
    }
}
