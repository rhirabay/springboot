package rhirabay;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Api(tags = "サンプルAPI", value = "サンプル")
@RestController
@Validated
public class SampleController {

    @ApiOperation("ユーザー登録")
    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = Void.class),
            @ApiResponse(code = 500, message = "システムエラー", response = ErrorResource.class)
    })
    public void register(@RequestBody @Valid User user) {
        return;
    }

    @Value
    @ApiModel(description = "詳細はこちら: http://sample.com")
    public static class ErrorResource {
        @ApiModelProperty(value = "エラーコード", example = "1000")
        private String errorCode;
        @ApiModelProperty(value = "エラーメッセージ", example = "予期せぬエラーが発生しました")
        private String errorMessage;
    }

    @ApiOperation("ユーザー検索")
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(
            @ApiParam(value = "ユーザーのID", required = true, example = "8cdd25ef-b8e4-4964-ab78-2ef5e16b54be")
            @Pattern(regexp = "[0-9a-f-]{36}") String id) {
        return new User("1234567890", "hogehoge", 30);
    }

    @Value
    public static class User {
        @ApiModelProperty(value = "ID", required = true, position = 1,
                example = "8cdd25ef-b8e4-4964-ab78-2ef5e16b54be")
        @NotNull
        @Pattern(regexp = "[0-9a-f-]{36}")
        private String id;

        @ApiModelProperty(value = "名前", required = true, position = 2,
                example = "hogehoge")
        @NotNull
        private String name;

        @ApiModelProperty(value = "年齢", required = true, position = 3,
                example = "30")
        @NotNull
        @Min(1)
        @Max(100)
        private int age;
    }
}
