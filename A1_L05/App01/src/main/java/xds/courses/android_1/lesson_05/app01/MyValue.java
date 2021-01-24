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

package xds.courses.android_1.lesson_05.app01;

/**
 * Class implements object with some integer value.
 * @author Mikhail Malakhov, 2016
 */
class MyValue {

    /** The internal field for store a value. */
    private int mValue = 0;

    /** The number of changes in value. */
    private int mChangeCount = 0;

    /**
     * Construct the new {@link MyValue} instance with default parameters.
     * */
    public MyValue() { super(); }

    /**
     * Construct the new {@link MyValue} instance with specified parameters.
     * @param value The initial value for this object.
     * */
    public MyValue(int value) { this(); mValue = value; }

    /**
     * Retrieves value from this object.
     * */
    public int get() { return mValue; }

    /**
     * Stores a new value to this object.
     * */
    public int put(int data) {
        if (mValue != data) {
            mValue = data;
            mChangeCount++;
        }
        return mValue;
    }

    /**
     * Checks that value was changed or not.
     * */
    public boolean isChanged() { return mChangeCount > 0; }

    /**
     * Returns the number of changes value in this object.
     * */
    public int getChangeCount() { return mChangeCount; }

    /**
     * Increments value in this object.
     * */
    public int inc() { return put(mValue + 1); }

    /**
     * Returns a string containing a concise, human-readable description of this object.
     * */
    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

}
