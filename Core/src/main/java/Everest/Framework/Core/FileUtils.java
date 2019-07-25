package Everest.Framework.Core;

import eu.medsea.mimeutil.MimeUtil;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;

public class FileUtils {
    public static String getMimeType(InputStream stream){
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(stream);
        return mimeTypes.toString();
    }

    public static String getMimeType(File file){
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(file);
        return mimeTypes.toString();
    }


    public static String getExtension(@Nonnull File file) {
        return MimeUtil.getExtension(file);
    }

}
