package com.app;

import com.app.server.HttpServerApp;

public class App 
{
    public static void main( String[] args )
    {
        HttpServerApp server = new HttpServerApp();

        server.start();
    }
}
