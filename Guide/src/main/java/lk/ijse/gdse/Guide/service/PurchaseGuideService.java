package lk.ijse.gdse.Guide.service;

import lk.ijse.gdse.Guide.dto.PurchaseGuideDTO;

import java.util.List;

public interface PurchaseGuideService {
    void savePurchaseGuide(PurchaseGuideDTO dto);
    void updatePurchaseGuide(PurchaseGuideDTO dto);
    void deletePurchaseGuide(Long id);
    PurchaseGuideDTO searchPurchaseGuideById(String id);
    List<PurchaseGuideDTO> getAllPurchaseGuideDetail();
}
