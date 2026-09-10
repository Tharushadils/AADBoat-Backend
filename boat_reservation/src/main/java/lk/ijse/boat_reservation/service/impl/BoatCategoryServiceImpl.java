package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.BoatCategoryDTO;
import lk.ijse.boat_reservation.entity.BoatCategory;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.BoatCategoryRepository;
import lk.ijse.boat_reservation.service.BoatCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoatCategoryServiceImpl implements BoatCategoryService {

    private final BoatCategoryRepository categoryRepository;

    public BoatCategoryServiceImpl(BoatCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public BoatCategoryDTO saveCategory(BoatCategoryDTO dto) {
        log.info("Execute method saveCategory()");

        try {
            if (dto == null) {
                throw new CustomException(
                        400,
                        "Category data cannot be null!"
                );
            }

            if (dto.getCategoryName() == null ||
                    dto.getCategoryName().trim().isEmpty()) {
                throw new CustomException(
                        400,
                        "Category name is required!"
                );
            }

            BoatCategory category = new BoatCategory();

            category.setCategoryName(dto.getCategoryName());
            category.setDescription(dto.getDescription());

            BoatCategory savedCategory =
                    categoryRepository.save(category);

            BoatCategoryDTO responseDTO = new BoatCategoryDTO();

            responseDTO.setCategoryId(savedCategory.getCategoryId());
            responseDTO.setCategoryName(savedCategory.getCategoryName());
            responseDTO.setDescription(savedCategory.getDescription());

            log.info("Category saved successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while saving category: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public BoatCategoryDTO updateCategory(Long id, BoatCategoryDTO dto) {
        log.info("Execute method updateCategory()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Valid Category ID is required for update!"
                );
            }

            if (dto == null) {
                throw new CustomException(
                        400,
                        "Category data cannot be null!"
                );
            }

            if (dto.getCategoryName() == null ||
                    dto.getCategoryName().trim().isEmpty()) {
                throw new CustomException(
                        400,
                        "Category name is required!"
                );
            }

            Optional<BoatCategory> optionalCategory =
                    categoryRepository.findById(id);

            if (optionalCategory.isEmpty()) {
                log.error(
                        "Category with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Category not found with ID: " + id
                );
            }

            BoatCategory category = optionalCategory.get();

            category.setCategoryName(dto.getCategoryName());
            category.setDescription(dto.getDescription());

            BoatCategory updatedCategory =
                    categoryRepository.save(category);

            BoatCategoryDTO responseDTO = new BoatCategoryDTO();

            responseDTO.setCategoryId(
                    updatedCategory.getCategoryId()
            );
            responseDTO.setCategoryName(
                    updatedCategory.getCategoryName()
            );
            responseDTO.setDescription(
                    updatedCategory.getDescription()
            );

            log.info("Category updated successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while updating category: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BoatCategoryDTO getCategoryById(Long id) {
        log.info("Execute method getCategoryById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Category ID: " + id
                );
            }

            Optional<BoatCategory> optionalCategory =
                    categoryRepository.findById(id);

            if (optionalCategory.isEmpty()) {
                log.error(
                        "Category with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Category not found with ID: " + id
                );
            }

            BoatCategory category = optionalCategory.get();

            BoatCategoryDTO responseDTO = new BoatCategoryDTO();

            responseDTO.setCategoryId(category.getCategoryId());
            responseDTO.setCategoryName(category.getCategoryName());
            responseDTO.setDescription(category.getDescription());

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching category: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoatCategoryDTO> getAllCategories() {
        log.info("Execute method getAllCategories()");

        try {
            List<BoatCategory> categories =
                    categoryRepository.findAll();

            List<BoatCategoryDTO> responseList =
                    new ArrayList<>();

            for (BoatCategory category : categories) {

                BoatCategoryDTO categoryDTO =
                        new BoatCategoryDTO();

                categoryDTO.setCategoryId(
                        category.getCategoryId()
                );
                categoryDTO.setCategoryName(
                        category.getCategoryName()
                );
                categoryDTO.setDescription(
                        category.getDescription()
                );

                responseList.add(categoryDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching all categories: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        log.info("Execute method deleteCategory()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Category ID: " + id
                );
            }

            Optional<BoatCategory> optionalCategory =
                    categoryRepository.findById(id);

            if (optionalCategory.isEmpty()) {
                log.error(
                        "Category with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Category not found with ID: " + id
                );
            }

            categoryRepository.deleteById(id);

            log.info("Category deleted successfully");

        } catch (Exception e) {
            log.error(
                    "Error occurred while deleting category: {}",
                    e.getMessage()
            );
            throw e;
        }
    }
}