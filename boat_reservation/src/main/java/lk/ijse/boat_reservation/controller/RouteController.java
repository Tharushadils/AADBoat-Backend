//package lk.ijse.boat_reservation.controller;
//
//import lk.ijse.boat_reservation.constant.CommonResponse;
//import lk.ijse.boat_reservation.dto.RouteDTO;
//import lk.ijse.boat_reservation.service.RouteService;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
//import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;
//
//@RestController
//@RequestMapping("/api/routes")
//public class RouteController {
//
//    private final RouteService routeService;
//
//    public RouteController(RouteService routeService) {
//        this.routeService = routeService;
//    }
//
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse saveRoute(@RequestBody RouteDTO routeDTO) {
//        routeService.saveRoute(routeDTO);
//        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
//    }
//
//    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse updateRoute(@RequestBody RouteDTO routeDTO) {
//        RouteDTO updatedRoute = routeService.updateRoute(routeDTO.getRouteId(), routeDTO);
//        return new CommonResponse(OPERATION_SUCCESS, updatedRoute, SUCCESS_MESSAGE);
//    }
//
//    @DeleteMapping(value = "/{routeId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse deleteRoute(@PathVariable long routeId) {
//        routeService.deleteRoute(routeId);
//        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
//    }
//
//    @GetMapping(value = "/{routeId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse getRouteById(@PathVariable long routeId) {
//        RouteDTO route = routeService.getRouteById(routeId);
//        return new CommonResponse(OPERATION_SUCCESS, route, SUCCESS_MESSAGE);
//    }
//
//    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse getAllRoutes() {
//        List<RouteDTO> routes = routeService.getAllRoutes();
//        return new CommonResponse(OPERATION_SUCCESS, routes, SUCCESS_MESSAGE);
//    }
//}