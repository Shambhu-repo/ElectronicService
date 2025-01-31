package com.lcwd.electronic.store.services.implementation;

import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.BadApiRequest;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.FileService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImplementation implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String uploadImage(MultipartFile file, String path) throws IOException {
        //abc.png
        String originalfilename = file.getOriginalFilename();
        logger.info("File name: {}", originalfilename);
        String fileName = UUID.randomUUID().toString();
        // assert originalfilename != null;
        String extension = originalfilename.substring(originalfilename.lastIndexOf("."));
        String fileNameWithExtension = fileName + extension;
        String fullPathWithFileName = path + fileNameWithExtension;
        logger.info("Full path: {}", fullPathWithFileName);

        if (extension.equalsIgnoreCase(".png")
                || extension.equalsIgnoreCase(".jpg")
                || extension.equalsIgnoreCase(".jpeg")
                || extension.equalsIgnoreCase(".gif")) {
//file save
            File folder = new File(path);
            if (!folder.exists()) {
                //create the folder
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));

            return fileNameWithExtension;
        } else {
            throw new BadApiRequest("file with this " + extension + " extension is not supported");
        }

    }

    @Override
    public InputStream getResorce(String path, String name) throws FileNotFoundException {
        String fullpath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullpath);

        return inputStream;
    }
}
