package by.bulaukin.orderservice.mapper;

import by.bulaukin.orderservice.model.OrdersEvent;
import by.bulaukin.orderservice.web.request.UpsertOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdersMapper {

    OrdersEvent requestToOrdersEvent(UpsertOrderRequest request);

}
