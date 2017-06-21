package com.lk.springmvc.web.controller;

import com.lk.springmvc.domain.model.spittr.Spittle;
import com.lk.springmvc.domain.mappers.spittr.SpittleRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luokai on 17-6-21.
 */
public class SpittleControllerTest {
    @Test
    public void testSpittles() throws Exception {
        List<Spittle> exectedSpittles = cueateSpittleList(20);

        SpittleRepository spittleRepository =
                Mockito.mock(SpittleRepository.class);
        Mockito.when(spittleRepository.findSpittles(565L, 20))
                .thenReturn(exectedSpittles);
        SpittleController controller = new SpittleController(spittleRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=565&count=20"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList", CoreMatchers.hasItems(exectedSpittles.toArray())));
    }

    private List<Spittle> cueateSpittleList(int count) {
        List<Spittle> spittleList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittleList.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittleList;
    }

    @Test
    public void testSpittle() throws Exception {
        Spittle exectedSpittle = new Spittle("Spittle", new Date());

        SpittleRepository spittleRepository =
                Mockito.mock(SpittleRepository.class);
        Mockito.when(spittleRepository.findOne(32523))
                .thenReturn(exectedSpittle);
        SpittleController controller = new SpittleController(spittleRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittle/32523"))
                .andExpect(MockMvcResultMatchers.view().name("spittle"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittle", exectedSpittle));
    }

    @Test
    public void testRegisterFrom() throws Exception {
        SpittleController controller = new SpittleController();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spittle/register"))
                .andExpect(MockMvcResultMatchers.view().name("registerFrom"));
    }

    @Test
    public void testProcessRegistration() throws Exception {
        SpittleRepository mockRepository =
                Mockito.mock(SpittleRepository.class);
        Spittle unsaved = new Spittle(null, "hello", null, 23.23, 121.12);
        Spittle saved = new Spittle(73, "hello", null, 23.23, 121.12);
        Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);

        SpittleController controller = new SpittleController(mockRepository);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/spittle/register")
                .param("message", "hello")
                .param("latitude", "23.23")
                .param("longitude", "121.12"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spittle/hello"));
        Mockito.verify(mockRepository, Mockito.atLeastOnce()).save(unsaved);
    }
}