package fr.dawan.cfa2022.dto;

import org.modelmapper.ModelMapper;


public class DtoTools {

	private static ModelMapper myMapper = new ModelMapper();
//	private static PropertyMap<LocationDto,VilleDto> villeMap = new  PropertyMap<LocationDto,VilleDto>(){
//		protected void configure() {
//			map().setNom(source.getName());
//			map().setSlug(source.getSlug());
//		}
//	};
	
	public static <TSource,TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {
		
		//ajouter les règles personnalisées
//		myMapper.typeMap(User.class, UserDto.class)
//		.addMappings(mapper->{
//			mapper.map(src->src.getFirstName(), UserDto::setFirstName);
//			mapper.map(src->src.getLastName(), UserDto::setLastName);
//		});
//		myMapper.typeMap(LocationDto.class, VilleDto.class)
//		.addMappings(mapper->{
//			mapper.map(src->src.getName(), VilleDto::setNom);
//			mapper.map(src->src.getSlug(), VilleDto::setSlug);
//		});
		
//		myMapper.addMappings(villeMap);
		return myMapper.map(obj, clazz);
	}
}
