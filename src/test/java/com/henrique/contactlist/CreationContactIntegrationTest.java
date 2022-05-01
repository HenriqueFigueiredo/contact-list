package com.henrique.contactlist;

import com.henrique.contactlist.controller.dto.NewContactDTO;
import com.henrique.contactlist.controller.dto.UpdateContactDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PATCH;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreationContactIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldBePossibleCreateNewContact() throws Exception {
        final URI uri = new URI(String.format("http://localhost:%s/api/v1/contacts", this.port));
        final NewContactDTO newContactDTO = new NewContactDTO("Henrique", "99990000", "henrique@email.com");
        final HttpEntity<NewContactDTO> request = new HttpEntity<>(newContactDTO, new HttpHeaders());

        final ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @Sql(scripts = {"/sqltest/import_contacts_query.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void shouldBePossibleListAllContacts() throws Exception {
        final URI uri = new URI(String.format("http://localhost:%s/api/v1/contacts", this.port));

        final ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @Sql(scripts = {"/sqltest/import_contacts_update.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void shouldBePossibleUpdateExistentContact() throws Exception {
        final URI uri = new URI(String.format("http://localhost:%s/api/v1/contacts/%s", this.port, "1d32fe02-c989-11ec-9d64-0242ac120002"));
        final UpdateContactDTO updateContactDTO = new UpdateContactDTO("Henrique", "99990000", "henrique@email.com");
        final HttpEntity<UpdateContactDTO> request = new HttpEntity<>(updateContactDTO, new HttpHeaders());

        this.restTemplate.put(uri, request);
    }

    @Test
    @Sql(scripts = {"/sqltest/import_contacts_patch.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void shouldBePossibleUpdateTheNameOfAnExistentContact() throws Exception {
        final URI uri = new URI(String.format("http://localhost:%s/api/v1/contacts/%s/name/%s", this.port, "1d32fefc-c989-11ec-9d64-0242ac120002", "NewName"));
        final UpdateContactDTO updateContactDTO = new UpdateContactDTO("Henrique", "99990000", "henrique@email.com");
        final HttpEntity<UpdateContactDTO> request = new HttpEntity<>(updateContactDTO, new HttpHeaders());
        this.restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        final ResponseEntity<String> result = this.restTemplate.exchange(uri, PATCH, request, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @Sql(scripts = {"/sqltest/import_contacts_delete.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void shouldBePossibleDeleteAnExistentContact() throws Exception {
        final URI uri = new URI(String.format("http://localhost:%s/api/v1/contacts/%s", this.port, "1d32ffec-c989-11ec-9d64-0242ac120002"));
        final HttpEntity<String> request = new HttpEntity<>("", new HttpHeaders());

        final ResponseEntity<String> result = this.restTemplate.exchange(uri, DELETE, request, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }
}
