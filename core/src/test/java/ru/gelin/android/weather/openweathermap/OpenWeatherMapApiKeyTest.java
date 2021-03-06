/*
 * Copyright 2010—2017 Denis Nelubin and others.
 *
 * This file is part of Weather Notification.
 *
 * Weather Notification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Weather Notification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Weather Notification.  If not, see http://www.gnu.org/licenses/.
 */

package ru.gelin.android.weather.openweathermap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.P})
@SuppressWarnings("deprecated")
public class OpenWeatherMapApiKeyTest {

    private final Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void testDefaultKey() {
        OpenWeatherMapApiKey key = new OpenWeatherMapApiKey(context);
//        assertEquals(OpenWeatherMapApiKey.DEFAULT_API_KEY, key.getKey());
        assertTrue(key.getKey().startsWith("4aba43"));
    }

    @Test
    public void testKeyFromPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(OpenWeatherMapApiKey.PREFERENCE_KEY, "test_key").commit();
        OpenWeatherMapApiKey key = new OpenWeatherMapApiKey(context);
        assertEquals("test_key", key.getKey());
        prefs.edit().remove(OpenWeatherMapApiKey.PREFERENCE_KEY).commit();
    }

    @Test
    public void testEmptyKeyInPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(OpenWeatherMapApiKey.PREFERENCE_KEY, "").commit();
        OpenWeatherMapApiKey key = new OpenWeatherMapApiKey(context);
        assertTrue(key.getKey().startsWith("4aba43"));
        prefs.edit().remove(OpenWeatherMapApiKey.PREFERENCE_KEY).commit();
    }

}
