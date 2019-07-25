package Everest.Framework.Tomcat;

import Everest.Framework.Http.FormFileCollection;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class FormFileParser {
    private Logger logger = LoggerFactory.getLogger(FormFileParser.class);
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    public FormFileCollection getFile(HttpServletRequest request){
        if(!ServletFileUpload.isMultipartContent(request)){
            return new FormFileCollection();
        }
        FormFileCollection files = new FormFileCollection();
        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        fileUpload.setFileSizeMax(MAX_FILE_SIZE);
        fileUpload.setSizeMax(MAX_REQUEST_SIZE);


        try {
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            if(fileItems != null && fileItems.size() > 0){
                for (FileItem item: fileItems){
                    if(!item.isFormField()){
                        files.add(new ServletFileItem(item));
                        logger.info("File: {}", item);
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return files;
    }
}
