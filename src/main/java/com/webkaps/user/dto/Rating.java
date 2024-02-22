package com.webkaps.user.dto;

import jakarta.persistence.Transient;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private int rating;
    private String feedback;
    private String hotelId;
}
