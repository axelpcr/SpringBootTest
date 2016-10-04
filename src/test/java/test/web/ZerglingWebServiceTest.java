package test.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import test.model.Zergling;
import test.service.ZerglingService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by axel on 04/10/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ZerglingWebService.class)
public class ZerglingWebServiceTest {


	@Autowired
	MockMvc mockMvc;

	@MockBean
	private ZerglingService zerglingService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testGetZergling() throws Exception {
		given(this.zerglingService.getZergling(1)).willReturn(new Zergling
				("MajorZerg", "160", "3"));

		this.mockMvc.perform(get("/zergling/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("MajorZerg")))
				.andExpect(jsonPath("$.height", is("160")))
				.andExpect(jsonPath("$.rank", is("3")));
	}

	@Test
	public void testGetZerglings() throws Exception {

		List<Zergling> zerglings = Arrays.asList(
				new Zergling("CaptainCarot", "125", "8"),
				new Zergling("CaptainNavet", "78", "5"));

		given(this.zerglingService.getZerglings()).willReturn(zerglings);

		this.mockMvc.perform(get("/zergling")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("CaptainCarot")))
				.andExpect(jsonPath("$[0].height", is("125")))
				.andExpect(jsonPath("$[0].rank", is("8")))
				.andExpect(jsonPath("$[1].name", is("CaptainNavet")))
				.andExpect(jsonPath("$[1].height", is("78")))
				.andExpect(jsonPath("$[1].rank", is("5")));
		verify(zerglingService, times(1)).getZerglings();
		verifyNoMoreInteractions(zerglingService);
	}

	@Test
	public void testSetZergling() throws Exception {
		given(this.zerglingService.setZergling(any(Zergling.class))).willReturn(new Zergling
				("MajorZerg", "160", "3"));

		this.mockMvc.perform(post("/zergling")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(zerglingService.setZergling(new Zergling())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is("MajorZerg")))
				.andExpect(jsonPath("$.height", is("160")))
				.andExpect(jsonPath("$.rank", is("3")));
		verify(zerglingService, times(2)).setZergling(any());
		verifyNoMoreInteractions(zerglingService);
	}

	@Test
	public void testUpdateZergling() throws Exception {

		given(this.zerglingService.updateZergling(any(Zergling.class))).willReturn(new Zergling
				("MajorZerg", "160", "3"));

		this.mockMvc.perform(put("/zergling")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(zerglingService.updateZergling(new Zergling())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("MajorZerg")))
				.andExpect(jsonPath("$.height", is("160")))
				.andExpect(jsonPath("$.rank", is("3")));
		verify(zerglingService, times(2)).updateZergling(any());
		verifyNoMoreInteractions(zerglingService);
	}

	@Test
	public void testDeleteZergling() throws Exception {

		mockMvc.perform(delete("/zergling/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		verify(zerglingService, times(1)).deleteZergling(any());
		verifyNoMoreInteractions(zerglingService);
	}
}