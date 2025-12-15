package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Model.Categoria;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria){
        CategoriaDto categoriaDto= new CategoriaDto();
        categoriaDto.setIdCategoria(categoria.getIdCategoria());
        categoriaDto.setUuid(categoria.getUuid());
        categoriaDto.setTipo(categoria.getTipo());
        categoriaDto.setPrecio(categoria.getPrecio());
        categoriaDto.setEstado(categoria.isEstado());
        if (categoria.getClasificadorRuat() != null) {
            categoriaDto.setClasificadorUuid(
                    categoria.getClasificadorRuat().getUuid()
            );
        }

        //categoriaDto.setClasificadorRuatDto(ClasificadorRuatMapper.toDto(categoria.getClasificadorRuat()));

        return categoriaDto;
    }


    public static Categoria toEntity(CategoriaDto categoriaDto){
        Categoria categoria=new Categoria();
        categoria.setIdCategoria(categoriaDto.getIdCategoria());
        categoria.setUuid(categoriaDto.getUuid());
        categoria.setTipo(categoriaDto.getTipo());
        categoria.setPrecio(categoriaDto.getPrecio());
        categoria.setEstado(categoriaDto.isEstado());

        //categoria.setClasificadorRuat(ClasificadorRuatMapper.toEntity(categoriaDto.getClasificadorRuatDto()));
        return categoria;
    }
}
