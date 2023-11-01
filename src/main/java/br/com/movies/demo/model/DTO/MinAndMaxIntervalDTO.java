package br.com.movies.demo.model.DTO;

import java.util.List;

public class MinAndMaxIntervalDTO {
    private List<AwardWinnerDTO> min;
    private List<AwardWinnerDTO> max;

    public MinAndMaxIntervalDTO(List<AwardWinnerDTO> min, List<AwardWinnerDTO> max) {
        this.min = min;
        this.max = max;
    }

    public MinAndMaxIntervalDTO() { }

    public List<AwardWinnerDTO> getMin() {
        return min;
    }

    public void setMin(List<AwardWinnerDTO> min) {
        this.min = min;
    }

    public List<AwardWinnerDTO> getMax() {
        return max;
    }

    public void setMax(List<AwardWinnerDTO> max) {
        this.max = max;
    }
}
