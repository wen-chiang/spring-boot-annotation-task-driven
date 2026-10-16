package com.sample.enterprise.devops.daas.util;

import com.google.common.base.Throwables;

import java.util.ArrayList;
import java.util.List;

public class ExceptionReviewerUtil {

    /**
     * Gets the project cause exception trace.
     *
     * @param throwable the throwable
     * @param line      the line
     * @return the project cause exception trace
     */
    @SuppressWarnings("UnstableApiUsage")
    public List<String> getProjectCauseExceptionTrace(Throwable throwable, int line) {

        List<String> ans = new ArrayList<>();
        Throwable lastLocalException = null;
        Throwable lastRemoteException = null;
        List<Throwable> causes = Throwables.getCausalChain(throwable);
        for (Throwable theOne : causes) {
            lastRemoteException = theOne;
            if (theOne.getClass().getName().startsWith("com.sample.enterprise.devops")) {
                lastLocalException = theOne;
            }
        }

        Throwable targetException = throwable;
        if (lastLocalException != null) {
            targetException = lastLocalException;
        } else if (lastRemoteException != null) {
            targetException = lastRemoteException;
        }

        String stackTrace = Throwables.getStackTraceAsString(targetException);
        String[] lines = stackTrace.split("\r\n\t");
        for (int i = 0; i < line && i < lines.length; i++) {
            ans.add(lines[i]);
        }
        return ans;

    }

}
