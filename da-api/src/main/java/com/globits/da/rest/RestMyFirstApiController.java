package com.globits.da.rest;

import com.globits.da.dto.MyFirstApiDTO;
import com.globits.da.service.MyFirstApiService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/myFirstApi")
public class RestMyFirstApiController {
    @Autowired
    MyFirstApiService myFirstApiService;

    //http://localhost:8085/da/myFirstApi
    @GetMapping
    public String myFirstApi() {
        return myFirstApiService.myFirstApiService();
    }

    //http://localhost:8085/da/myFirstApi
    @PostMapping
    public MyFirstApiDTO postFirstApi(@RequestBody MyFirstApiDTO myFirstApiDTO) {
        return myFirstApiDTO;
    }

    //http://localhost:8085/da/myFirstApi/myDTO
    @PostMapping("/myDTO")
    public MyFirstApiDTO createMyDto(
            @RequestParam String code,
            @RequestParam String name,
            @RequestParam Integer age) {
        MyFirstApiDTO myDto = new MyFirstApiDTO();
        myDto.setCode(code);
        myDto.setName(name);
        myDto.setAge(age);
        return myDto;
    }

    //http://localhost:8085/da/myFirstApi/m2/123
    @PostMapping("/m2/{id}")
    public MyFirstApiDTO create(@PathVariable("id") String id, @RequestBody MyFirstApiDTO myFirstApiDTO) {
        System.out.println("Receive is :" + id);
        return myFirstApiDTO;
    }

    //http://localhost:8085/da/myFirstApi/myFirst
    @PostMapping("/myFirst")
    public MyFirstApiDTO createP11(HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        MyFirstApiDTO myFirstApiDTO = new MyFirstApiDTO();
        if (contentType != null && contentType.contains("application/json")) {
            String json = new BufferedReader(request.getReader()).lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            myFirstApiDTO = objectMapper.readValue(json, MyFirstApiDTO.class);
        } else if (contentType != null && contentType.contains("multipart/form-data")) {
            myFirstApiDTO.setCode(request.getParameter("code"));
            myFirstApiDTO.setName(request.getParameter("name"));
            String age = request.getParameter("age");
            if (age != null) {
                myFirstApiDTO.setAge(Integer.parseInt(age));
            }
        }
        return myFirstApiDTO;
    }

    //http://localhost:8085/da/myFirstApi/postFile
    @PostMapping("/postFile")
    public String uploadFile(@RequestParam("file") MultipartFile[] submissions) {
        StringBuilder response = new StringBuilder();
        for (MultipartFile f : submissions) {
            response.append("File Name: ").append(f.getOriginalFilename()).append("\n");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(f.getInputStream()))) {
                String lines = br.lines().collect(Collectors.joining("\n"));
                response.append("File content:\n").append(lines).append("\n\n");
                System.out.println("File Name: " + f.getOriginalFilename());
                System.out.println("File Content:");
                System.out.println(lines);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return response.toString();
    }

    @PostMapping("/notUseRequestBody")
    public MyFirstApiDTO notUseRequestBody(HttpServletRequest request) throws IOException {
        String json = new BufferedReader(request.getReader()).lines().collect(Collectors.joining());
        System.out.println("Received JSON:" + json);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, MyFirstApiDTO.class);
    }

}
