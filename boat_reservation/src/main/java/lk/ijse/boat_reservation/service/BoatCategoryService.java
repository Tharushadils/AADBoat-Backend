package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.BoatCategoryDTO;
import java.util.List;

public interface BoatCategoryService {
    BoatCategoryDTO saveCategory(BoatCategoryDTO categoryDTO);
    BoatCategoryDTO updateCategory(Long id, BoatCategoryDTO categoryDTO);
    BoatCategoryDTO getCategoryById(Long id);
    List<BoatCategoryDTO> getAllCategories();
    void deleteCategory(Long id);
}