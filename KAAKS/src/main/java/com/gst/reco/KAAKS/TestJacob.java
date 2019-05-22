package com.gst.reco.KAAKS;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class TestJacob {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        File file = new File("D:\\demo.xlsm");
        String macroName = "DeleteAllMacros";
        callExcelMacro(file, macroName);

    }

    private static void callExcelMacro(File file, String macroName) {
        ComThread.InitSTA(true);
        final ActiveXComponent excel = new ActiveXComponent("Excel.Application");
        try{
            excel.setProperty("EnableEvents", new Variant(false));

            Dispatch workbooks = excel.getProperty("Workbooks")
                    .toDispatch();

            Dispatch workBook = Dispatch.call(workbooks, "Open",
                    file.getAbsolutePath()).toDispatch();

            // Calls the macro
            Variant V1 = new Variant( file.getName() + macroName);
            //
            Variant result = Dispatch.call(excel, "Run", new Variant("\'"+file.getName()+"\'"+ macroName));

           // Variant result = Dispatch.call(excel, "Run", V1);

            // Saves and closes
            Dispatch.call(workBook, "Save");

            com.jacob.com.Variant f = new com.jacob.com.Variant(true);
            Dispatch.call(workBook, "Close", f);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excel.invoke("Quit", new Variant[0]);
            ComThread.Release();
        }
    }
}