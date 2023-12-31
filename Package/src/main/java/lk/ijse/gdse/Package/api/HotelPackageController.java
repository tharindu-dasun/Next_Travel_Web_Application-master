package lk.ijse.gdse.Package.api;

import lk.ijse.gdse.Package.dto.HotelPackageDTO;
import lk.ijse.gdse.Package.service.HotelPackageService;
import lk.ijse.gdse.Package.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@RestController
@RequestMapping("/package")
@CrossOrigin
public class HotelPackageController {
    @Autowired
    HotelPackageService hotelPackageService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil savePackage(@RequestBody HotelPackageDTO packageDTO){
        System.out.println(packageDTO.toString());
        hotelPackageService.savePackage(packageDTO);
        return new ResponseUtil(200,"Success",packageDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllHotelPackages(){
        return new ResponseUtil(200,"Success",hotelPackageService.getAllHotelPackageDetail());
    }

    @PutMapping()
    public ResponseUtil updateHotelPackage(@RequestBody HotelPackageDTO dto){
        hotelPackageService.updatePackage(dto);
        return new ResponseUtil(200,"Success!"+": Updated.!",null);
    }

    @DeleteMapping(params = {"packageId"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteHotelPackage(@RequestParam Long packageId){
        hotelPackageService.deletePackage(packageId);
        return new ResponseUtil(200,"Success",packageId);
    }
}
