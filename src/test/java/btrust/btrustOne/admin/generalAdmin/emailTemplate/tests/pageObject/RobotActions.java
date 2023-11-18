package btrust.btrustOne.admin.generalAdmin.emailTemplate.tests.pageObject;


import java.awt.*;
import java.awt.event.InputEvent;

public class RobotActions {

    public static void robotPressKey(int keyEvent)
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.keyPress(keyEvent);
        robot.keyRelease(keyEvent);
    }

    public static void robotPressKeyWithoutRelease(int keyEvent)
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.keyPress(keyEvent);
    }

    public static void robotReleaseKey(int keyEvent)
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.keyRelease(keyEvent);
    }

    public static void robotLongPressKey(int keyEvent)
    {
        Robot bot = null;
        try
        {
            bot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        bot.setAutoDelay(50);

        int duration = 3000;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < duration)
        {
            bot.keyPress(keyEvent);
        }
        bot.keyRelease(keyEvent);
    }

    public static void robotPressTwoKeys(int keyEvent, int keyEvent2)
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.keyPress(keyEvent);
        robot.keyPress(keyEvent2);
        robot.keyRelease(keyEvent2);
        robot.keyRelease(keyEvent);
    }

    public static void robotPressMouseWithoutRelease()
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public static void robotReleaseMousePress()
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }


    public static void robotMouseDoubleClick()
    {
        robotMouseClick();
        robotMouseClick();
    }

    public static void robotMouseThreeClicks()
    {
        robotMouseClick();
        robotMouseClick();
        robotMouseClick();
    }

    public static void robotMouseClick()
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void robotMouseWheel(int wheelAmt)
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.mouseWheel(wheelAmt);
    }

    public static void robotMouseMove(int x, int y)
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.mouseMove(x, y);
    }

    public static void mouseRightClick()
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }






}
