//package lk.ijse.boat_reservation.service.impl;
//
//import lk.ijse.boat_reservation.dto.RouteDTO;
//import lk.ijse.boat_reservation.entity.Route;
//import lk.ijse.boat_reservation.exception.CustomException;
//import lk.ijse.boat_reservation.repository.RouteRepository;
//import lk.ijse.boat_reservation.service.RouteService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class RouteServiceImpl implements RouteService {
//
//    private final RouteRepository routeRepository;
//
//    public RouteServiceImpl(RouteRepository routeRepository) {
//        this.routeRepository = routeRepository;
//    }
//
//    @Override
//    @Transactional
//    public RouteDTO saveRoute(RouteDTO dto) {
//        log.info("Execute method saveRoute()");
//
//        try {
//            if (dto == null) {
//                throw new CustomException(
//                        400,
//                        "Route data cannot be null!"
//                );
//            }
//
//            Route route = new Route();
//
//            route.setRouteName(dto.getRouteName());
//            route.setStartPoint(dto.getStartPoint());
//            route.setDestinationPoint(dto.getDestinationPoint());
//            route.setEstimatedDurationHours(
//                    dto.getEstimatedDurationHours()
//            );
//            route.setBaseRouteFee(dto.getBaseRouteFee());
//
//            Route savedRoute =
//                    routeRepository.save(route);
//
//            RouteDTO responseDTO = new RouteDTO();
//
//            responseDTO.setRouteId(
//                    savedRoute.getRouteId()
//            );
//            responseDTO.setRouteName(
//                    savedRoute.getRouteName()
//            );
//            responseDTO.setStartPoint(
//                    savedRoute.getStartPoint()
//            );
//            responseDTO.setDestinationPoint(
//                    savedRoute.getDestinationPoint()
//            );
//            responseDTO.setEstimatedDurationHours(
//                    savedRoute.getEstimatedDurationHours()
//            );
//            responseDTO.setBaseRouteFee(
//                    savedRoute.getBaseRouteFee()
//            );
//
//            log.info("Route saved successfully");
//
//            return responseDTO;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while saving route: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public RouteDTO updateRoute(
//            Long id,
//            RouteDTO dto) {
//
//        log.info("Execute method updateRoute()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Route ID: " + id
//                );
//            }
//
//            if (dto == null) {
//                throw new CustomException(
//                        400,
//                        "Route data cannot be null!"
//                );
//            }
//
//            Optional<Route> routeOptional =
//                    routeRepository.findById(id);
//
//            if (routeOptional.isEmpty()) {
//                log.error(
//                        "Route with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Route not found with id: " + id
//                );
//            }
//
//            Route route = routeOptional.get();
//
//            route.setRouteName(dto.getRouteName());
//            route.setStartPoint(dto.getStartPoint());
//            route.setDestinationPoint(
//                    dto.getDestinationPoint()
//            );
//            route.setEstimatedDurationHours(
//                    dto.getEstimatedDurationHours()
//            );
//            route.setBaseRouteFee(
//                    dto.getBaseRouteFee()
//            );
//
//            Route updatedRoute =
//                    routeRepository.save(route);
//
//            RouteDTO responseDTO = new RouteDTO();
//
//            responseDTO.setRouteId(
//                    updatedRoute.getRouteId()
//            );
//            responseDTO.setRouteName(
//                    updatedRoute.getRouteName()
//            );
//            responseDTO.setStartPoint(
//                    updatedRoute.getStartPoint()
//            );
//            responseDTO.setDestinationPoint(
//                    updatedRoute.getDestinationPoint()
//            );
//            responseDTO.setEstimatedDurationHours(
//                    updatedRoute.getEstimatedDurationHours()
//            );
//            responseDTO.setBaseRouteFee(
//                    updatedRoute.getBaseRouteFee()
//            );
//
//            log.info("Route updated successfully");
//
//            return responseDTO;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while updating route: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public RouteDTO getRouteById(Long id) {
//        log.info("Execute method getRouteById()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Route ID: " + id
//                );
//            }
//
//            Optional<Route> routeOptional =
//                    routeRepository.findById(id);
//
//            if (routeOptional.isEmpty()) {
//                log.error(
//                        "Route with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Route not found with id: " + id
//                );
//            }
//
//            Route route = routeOptional.get();
//
//            RouteDTO dto = new RouteDTO();
//
//            dto.setRouteId(
//                    route.getRouteId()
//            );
//            dto.setRouteName(
//                    route.getRouteName()
//            );
//            dto.setStartPoint(
//                    route.getStartPoint()
//            );
//            dto.setDestinationPoint(
//                    route.getDestinationPoint()
//            );
//            dto.setEstimatedDurationHours(
//                    route.getEstimatedDurationHours()
//            );
//            dto.setBaseRouteFee(
//                    route.getBaseRouteFee()
//            );
//
//            return dto;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching route: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<RouteDTO> getAllRoutes() {
//        log.info("Execute method getAllRoutes()");
//
//        try {
//            List<Route> routes =
//                    routeRepository.findAll();
//
//            List<RouteDTO> responseList =
//                    new ArrayList<>();
//
//            for (Route route : routes) {
//
//                RouteDTO dto = new RouteDTO();
//
//                dto.setRouteId(
//                        route.getRouteId()
//                );
//                dto.setRouteName(
//                        route.getRouteName()
//                );
//                dto.setStartPoint(
//                        route.getStartPoint()
//                );
//                dto.setDestinationPoint(
//                        route.getDestinationPoint()
//                );
//                dto.setEstimatedDurationHours(
//                        route.getEstimatedDurationHours()
//                );
//                dto.setBaseRouteFee(
//                        route.getBaseRouteFee()
//                );
//
//                responseList.add(dto);
//            }
//
//            return responseList;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching all routes: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public void deleteRoute(Long id) {
//        log.info("Execute method deleteRoute()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Route ID: " + id
//                );
//            }
//
//            Optional<Route> routeOptional =
//                    routeRepository.findById(id);
//
//            if (routeOptional.isEmpty()) {
//                log.error(
//                        "Route with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Route not found with id: " + id
//                );
//            }
//
//            routeRepository.deleteById(id);
//
//            log.info(
//                    "Route deleted successfully with id: {}",
//                    id
//            );
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while deleting route: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//}