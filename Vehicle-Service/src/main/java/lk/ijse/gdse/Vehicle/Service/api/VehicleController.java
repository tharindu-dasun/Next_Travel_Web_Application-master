package lk.ijse.gdse.Vehicle.Service.api;

import lk.ijse.gdse.Vehicle.Service.dto.VehicleDTO;
import lk.ijse.gdse.Vehicle.Service.service.VehicleService;
import lk.ijse.gdse.Vehicle.Service.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil addVehicle(@RequestPart("vehicleFiles") MultipartFile[] file, @RequestPart("vehicle") VehicleDTO vehicleDTO) {

        for (MultipartFile myFile : file) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));
                System.out.println(projectPath);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, "Registration Failed.Try Again Latter", null);
            }
        }

        vehicleService.saveVehicle(vehicleDTO);
        return new ResponseUtil(200, "Registration Successfully....", vehicleDTO);
    }


    @PostMapping(path = "/uploadImg/{vehicleId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil uploadImagesAndPath(@RequestPart("image1") MultipartFile image1, @RequestPart("image2") MultipartFile image2, @RequestPart("image3") MultipartFile image3, @RequestPart("image4") MultipartFile image4, @PathVariable Long vehicleId) {
        try {
            System.out.println(image1.getOriginalFilename());
            System.out.println("Upload Image");

            String projectPath = String.valueOf(new File("E:\\imageSave\\uploads"));
            File uploadsDir = new File(projectPath + "\\carImage");
            uploadsDir.mkdir();

            image1.transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + image1.getOriginalFilename()));
            image2.transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + image2.getOriginalFilename()));
            image3.transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + image3.getOriginalFilename()));
            image4.transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + image4.getOriginalFilename()));

            String carFrontViewPath = projectPath + "\\carImage" + image1.getOriginalFilename();
            String carBackViewPath = projectPath + "\\carImage" + image2.getOriginalFilename();
            String carSideViewPath = projectPath + "\\carImage" + image3.getOriginalFilename();
            String carInteriorViewPath = projectPath + "\\carImage" + image4.getOriginalFilename();


            vehicleService.uploadCarImage(carFrontViewPath, carBackViewPath, carSideViewPath, carInteriorViewPath,vehicleId);

            return new ResponseUtil(200, "Uploaded", null);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseUtil(500,e.getMessage(),null);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllVehicles(){
        return new ResponseUtil(200,"Success",vehicleService.getAllVehicleDetail());
    }


    @PutMapping()
    public ResponseUtil updateGuide(@RequestBody VehicleDTO dto){
        vehicleService.updateVehicle(dto);
        return new ResponseUtil(200,"Success!"+": Updated.!",null);
    }


    @DeleteMapping(params = {"vehicleId"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteGuide(@RequestParam Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseUtil(200,"Success",vehicleId);
    }

}
