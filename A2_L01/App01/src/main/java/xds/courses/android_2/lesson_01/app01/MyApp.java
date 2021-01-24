/**
 * Copyright (C) 2013 xDevStudio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */

package xds.courses.android_2.lesson_01.app01;

import android.app.Application;

import xds.courses.android_2.lesson_01.app01.db.DBEmpl;

/**
 * The {@link Application} class for this app.
 * @author Mikhail.Malakhov
 * */
public class MyApp extends Application {

    /** The {@link MyApp} instance. */
    private static MyApp sInstance = null;

    /** The database. */
    private DBEmpl mDBEmpl = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDBEmpl = new DBEmpl(this);
    }

    /**
     * Returns current instance of this {@link MyApp} class.
     * */
    public static MyApp getInstance() { return sInstance; }

    /**
     * Returns {@link DBEmpl} instance.
     * */
    public static DBEmpl getDB() { return getInstance().mDBEmpl; }

}