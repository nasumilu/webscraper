/*
 *    Copyright 2022 Michael Lucas
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.nasumilu.webscrapper.exception;

/**
 * Exception class indicating that an expected element was not found!
 */
public class InvalidElementException extends IllegalArgumentException {

    /**
     * InvalidElementException constructor
     * @param message the exception's message
     */
    public InvalidElementException(String message) {
        super(message);
    }

    /**
     * Static factory to create an InvalidElementException
     * @param expectedTagName the name of the expected element tag name
     * @return An instance of InvalidElementException with the message "Expected element for tag name %s, none found!".
     */
    public static InvalidElementException createElementByTagNameNotFound(String expectedTagName) {
        return new InvalidElementException(String.format("Expected element for tag name %s, none found!", expectedTagName));
    }
}
