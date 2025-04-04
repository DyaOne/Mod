package com.libs.api.mouse;

public class MouseAPI {
    private static boolean isMouseLocked = false;
    private static boolean isLeftPressed = false;
    private static double mouseX = 0;
    private static double mouseY = 0;

    public static void updateMouse(double x, double y) {
        mouseX = x;
        mouseY = y;
    }

    public static void setLeftPressed(boolean pressed) {
        isLeftPressed = pressed;
    }

    public static void lockMouse() {
        isMouseLocked = true;
    }

    public static void unlockMouse() {
        isMouseLocked = false;
    }

    public static boolean isMouseLocked() {
        return isMouseLocked;
    }

    public static boolean isLeftPressed() {
        return isLeftPressed;
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }
}

