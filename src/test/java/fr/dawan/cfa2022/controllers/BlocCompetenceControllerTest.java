package fr.dawan.cfa2022.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.cfa2022.dto.BlocCompetencesDto;
import fr.dawan.cfa2022.dto.StringDto;
import fr.dawan.cfa2022.interceptors.TokenInterceptor;
import fr.dawan.cfa2022.services.BlocCompetencesService;
@SpringBootTest
@AutoConfigureMockMvc
class BlocCompetenceControllerTest {

	@Autowired
	MockMvc mockMvc; 
	
	@MockBean
	private TokenInterceptor tokenInterceptor;
	
	@MockBean
	BlocCompetencesService blocCompetencesService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	List<BlocCompetencesDto> blocCompetences = new ArrayList<BlocCompetencesDto>();
	
	@BeforeEach
	public void setup() throws Exception {
		when(tokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		blocCompetences.add(new BlocCompetencesDto(1 ,"Titre1","Description1",1,0) );
		blocCompetences.add(new BlocCompetencesDto(2 ,"Titre2","Description2",1,0) );
		
	}
	
	
	@Test
	public void testGetAll() throws Exception {
		when(blocCompetencesService.getAll(0, 2, "")).thenReturn(blocCompetences);
		mockMvc.perform(get("/blocsCompetence"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(blocCompetences.size())))
		.andExpect(jsonPath("$[0].titre",is(blocCompetences.get(0).getTitre()) ))	
		.andExpect(jsonPath("$[0].description",is(blocCompetences.get(0).getDescription()) ))	

		;
	}
	@Test
	public void testGetById() throws Exception {
		int id = (int) blocCompetences.get(0).getId();
		
		when(blocCompetencesService.getById(id)).thenReturn(blocCompetences.get(0));
		
		mockMvc.perform(get("/blocsCompetence/{id}"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.titre",is(blocCompetences.get(0).getTitre()) ))	
		.andExpect(jsonPath("$.description",is(blocCompetences.get(0).getDescription()) ))	
		.andExpect(jsonPath("$.id", is(id)));

	}
	@Test
	public void testDeleteById()  throws Exception {
		
		long id = blocCompetences.get(0).getId();
		StringDto delete = new StringDto();
		delete.setText("suppression effectuée");
		
		doNothing().when(blocCompetencesService).delete(id);;
		
		String res = mockMvc.perform(delete("/blocsCompetence/{id}",id))
		.andExpect(status().isAccepted())	
		.andReturn().getResponse().getContentAsString()
		;
		assertEquals(delete, res);
	}
	@Test
	void testInsert() throws Exception {
		
		BlocCompetencesDto blocCompetencesDto = blocCompetences.get(0);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		String ActiviteTypeToActiviteTypeStr = objectMapper.writeValueAsString(blocCompetencesDto);
		
		when(blocCompetencesService.saveOrUpdate(blocCompetencesDto)).thenReturn(blocCompetencesDto);
		mockMvc.perform(post("/activiteTypes")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(ActiviteTypeToActiviteTypeStr)
				.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isCreated());
	}
	
	
}
