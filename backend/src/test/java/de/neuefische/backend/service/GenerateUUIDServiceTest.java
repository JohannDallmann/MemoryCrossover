package de.neuefische.backend.service;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenerateUUIDServiceTest {

    GenerateUUIDService generateUUIDService = new GenerateUUIDService();

    @Test
    void testGenerateUUID_returns_uuid() {
        // when
        String actualUUID = generateUUIDService.generateUUID();

        // then
        assertNotNull(actualUUID);
    }

}