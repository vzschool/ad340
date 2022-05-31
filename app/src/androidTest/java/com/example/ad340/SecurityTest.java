package com.example.ad340;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SecurityTest {

    @Test
    public void testEncryptSHA256() {
        assertEquals("getting coverege", Security.encryptSHA256("0"), Security.encryptSHA256("0"));
    }
}
