package com.clustereddatawarehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FxDealController.class)
@AutoConfigureMockMvc
public class FxDealsRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectWriter getObjectWriter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void fxDealHappyScenario() throws Exception {
        ObjectWriter ow = getObjectWriter();

        List<FxDealRequestDetails> jsonFxDealList = Arrays.asList(
                new FxDealRequestDetails(100L, "USD", "JOD", LocalDateTime.now(), BigDecimal.valueOf(100.25)),
                new FxDealRequestDetails(101L, "JOD", "USD", LocalDateTime.now(), BigDecimal.valueOf(1075.21)),
                new FxDealRequestDetails(102L, "USD", "ERU", LocalDateTime.now(), BigDecimal.valueOf(102.65)),
                new FxDealRequestDetails(103L, "ERU", "JOD", LocalDateTime.now(), BigDecimal.valueOf(75.85))
        );
        String requestJson = ow.writeValueAsString(jsonFxDealList);

        mockMvc.perform(post("/api/v1/fx-deal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void fxDealBadRequestScenario() throws Exception {
        ObjectWriter ow = getObjectWriter();

        List<FxDealRequestDetails> jsonFxDealList = Arrays.asList(
                new FxDealRequestDetails(-100L, "$", "JOD", LocalDateTime.now(), BigDecimal.valueOf(100.25)),
                new FxDealRequestDetails(101L, "12cs", "USD", LocalDateTime.now(), BigDecimal.valueOf(1075.21)),
                new FxDealRequestDetails(102L, "USD", "102", LocalDateTime.now(), BigDecimal.valueOf(102.65)),
                new FxDealRequestDetails(-103L, "ERU", "JODxc", LocalDateTime.now(), BigDecimal.valueOf(75.85))
        );
        String requestJson = ow.writeValueAsString(jsonFxDealList);

        mockMvc.perform(post("/api/v1/fx-deal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}
