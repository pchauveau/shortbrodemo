package com.shortbrodemo.pch.shortbrodemo.utils;

import android.os.Environment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.MockClassLoader;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Environment.class, File.class}) // Prepare the static classes for mocking
public class UtilsImageTest {
    @Mock
    File file;
    @Mock
    File fileEasyMock;

    List<String> strings;

    @Before
    public void setUp() throws Exception {
        strings = new ArrayList<>();
        strings.add("untest/nom1");
        strings.add("untest/bla/nom2");
        strings.add("untest///nom3");
        strings.add("untest/test_faef/nom4");
        strings.add("/untest_sxgrh/nom5.jpg");

        MockitoAnnotations.initMocks(this);

        // Setup mocking for Environment
        mockStatic(Environment.class);

        final File fileMock = PowerMockito.mock(File.class);
        Mockito.when(fileMock.exists()).thenReturn(true);

        // Make the Environment class return a mocked external storage directory
        when(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).thenReturn(file);
    }

    @Test
    public void gettingImageName() throws Exception {
        assertEquals("nom1", UtilsImage.gettingImageName(strings.get(0)));
        assertEquals("nom2", UtilsImage.gettingImageName(strings.get(1)));
        assertEquals("nom3", UtilsImage.gettingImageName(strings.get(2)));
        assertEquals("nom4", UtilsImage.gettingImageName(strings.get(3)));
        assertEquals("nom5.jpg", UtilsImage.gettingImageName(strings.get(4)));
    }

    @Test
    public void isImageAlreadyLocal() throws Exception {
    }

    @Test
    public void saveLocally() throws Exception {
    }

    @Test
    public void getLocally() throws Exception {
    }

    @Test
    public void saveAllLocally() throws Exception {
    }

}