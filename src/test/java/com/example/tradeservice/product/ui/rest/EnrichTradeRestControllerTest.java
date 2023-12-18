package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.MatchingApi;
import com.example.tradeservice.shared.MyFileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartException;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class EnrichTradeRestControllerTest {

    private static final String ENDPOINT_ENRICH = "/api/v1/enrich";

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private MatchingApi matchingApi;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getEnrich_return_helloMessage() throws Exception {
        // when then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/enrich"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"api\": \"trades matching api\"}"));
    }

    @Test
    void postEnrich_return422_whenNoMultipartFilePassed() throws Exception {
        // when
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(ENDPOINT_ENRICH))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        // then
        MultipartException someException = (MultipartException) result.getResolvedException();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Current request is not a multipart request");
        assertThat(someException).isInstanceOf(MultipartException.class);
    }

    @Test
    void postEnrich_Return200_whenMultipartFilePassed() throws Exception {
        // given
        MockMultipartFile firstFile = new MockMultipartFile(
                "file",
                "singleTrade.csv",
                "text/csv",
                MyFileUtils.readCsvFile("src/test/resources/trade/singleTrade.csv"));

        // when then
        when(matchingApi.match(any()))
                .thenReturn(List.of(new MatchedTradeDto("20231217", "Mocked Product Name", "EUR", BigDecimal.ONE)));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .multipart(ENDPOINT_ENRICH)
                        .file(firstFile))
                .andExpect(status().is(200))
                .andExpect(content().string("""
                        date,productName,currency,price
                        20231217,"Mocked Product Name",EUR,1
                        """));
    }
}
