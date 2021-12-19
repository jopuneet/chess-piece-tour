package com.truecaller.piecetour.services;

/**
 * The type Output service.
 *
 * @author puneet created on 11/12/21
 */
public class OutputService {

    private static OutputService instance = new OutputService();

    private OutputService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OutputService getInstance() {
        return instance;
    }

    /**
     * Print string to be displayed as a single line.
     *
     * @param message
     *            the message
     */
    public void print(String message) {
        System.out.println(message);
    }
}
