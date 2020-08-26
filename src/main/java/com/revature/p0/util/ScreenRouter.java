package com.revature.p0.util;

import com.revature.p0.screens.Screen;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * switches screen, all screens will use navigate to go from one screen to the next
 */

public class ScreenRouter {

    private Set<Screen> screens = new HashSet<>();

    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }

    public void navigate(String route) throws IOException {
        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found with " + route))
                .render();
    }
}
