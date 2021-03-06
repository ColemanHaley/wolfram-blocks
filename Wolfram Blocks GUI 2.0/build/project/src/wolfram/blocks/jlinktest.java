package wolfram.blocks;
import com.wolfram.jlink.*;

public class jlinktest {



	public static void main(String[] args) {


		KernelLink ml = null;

    try {
        ml = MathLinkFactory.createKernelLink("-linkmode launch -linkname \"C:\\\\Program Files\\\\Wolfram Research\\\\Mathematica\\\\10.4\\\\mathkernel.exe\"");
    } catch (MathLinkException e) {
        System.out.println("Fatal error opening link: " + e.getMessage());
        return;
    }

    try {
        // Get rid of the initial InputNamePacket the kernel will send
        // when it is launched.
        ml.discardAnswer();

        ml.evaluate("<<MyPackage.m");
        ml.discardAnswer();

        ml.evaluate("2+2");
        ml.waitForAnswer();

        int result = ml.getInteger();
        System.out.println("2 + 2 = " + result);

        // Here's how to send the same input, but not as a string:
        ml.putFunction("EvaluatePacket", 1);
        ml.putFunction("Plus", 2);
        ml.put(3);
        ml.put(3);
        ml.endPacket();
        ml.waitForAnswer();
        result = ml.getInteger();
        System.out.println("3 + 3 = " + result);

        // If you want the result back as a string, use evaluateToInputForm
        // or evaluateToOutputForm. The second arg for either is the
        // requested page width for formatting the string. Pass 0 for
        // PageWidth->Infinity. These methods get the result in one
        // step--no need to call waitForAnswer.
        String strResult = ml.evaluateToOutputForm("4+4", 0);
        System.out.println("4 + 4 = " + strResult);

    } catch (MathLinkException e) {
        System.out.println("MathLinkException occurred: " + e.getMessage());
    } finally {
        ml.close();
    }
}
}

