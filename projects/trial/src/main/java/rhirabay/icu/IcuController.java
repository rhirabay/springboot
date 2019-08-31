package rhirabay.icu;

import com.ibm.icu.text.Transliterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/icu")
public class IcuController {

    @GetMapping("/full2half")
    @ResponseBody
    public String toHalf(@RequestParam String target) {
        Transliterator full2half = Transliterator.getInstance("Fullwidth-Halfwidth");
        return full2half.transliterate(target);
    }

    @GetMapping("/half2full")
    @ResponseBody
    public String toFull(@RequestParam String target) {
        try {
            Transliterator half2full = Transliterator.getInstance("Halfwidth-Fullwidth");
            return half2full.transliterate(target);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    public static void main(String[] args) {
        Transliterator half2full = Transliterator.getInstance("Halfwidth-Fullwidth");
        String full = half2full.transliterate("0123456789ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝABCDEFGHIJKLMNOPQRSTUVWXYZﾞﾟ%20\\｢｣,.()-/");

        log.info("half2full: {}", full);

        Transliterator full2half = Transliterator.getInstance("Fullwidth-Halfwidth");
        String half = full2half.transliterate(full);

        log.info("full2half: {}", half);

        log.info("濁音半角: {}", half2full.transliterate("ｶﾞｷﾞｸﾞｹﾞｺﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ"));
        log.info("濁音全角: {}", full2half.transliterate("ガギグゲゴパピプペポガギグゲゴパピプペポ"));

        log.info("長音: {} ({})", full2half.transliterate("➖ ー ー "), full2half.transliterate("ー ー ").indexOf("-"));
    }
}
