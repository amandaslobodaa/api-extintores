package extintores_api.demo.exception
import extintores_api.demo.dto.ErroView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.naming.AuthenticationException

@RestControllerAdvice
class ExceptionHandler
@ExceptionHandler(NotFoundException::class)
@ResponseStatus(HttpStatus.NOT_FOUND)
fun produtoNaoEncontrado(exception: NotFoundException, request: HttpServletRequest): ErroView {
    return ErroView(
        status = HttpStatus.NOT_FOUND.value(),
        error = HttpStatus.NOT_FOUND.name,
        message = "O produto não foi encontrado.",
        path = request.servletPath
    )
}
@ExceptionHandler(MethodArgumentNotValidException::class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
fun campoNaoPreenchido(exception: MethodArgumentNotValidException, request: HttpServletRequest): ErroView {
    val erroFormat = exception.bindingResult.fieldErrors.joinToString(separator = "; ")
    { error -> "[${error.field}]: ${error.defaultMessage}" }
    return ErroView(
        status = HttpStatus.BAD_REQUEST.value(),
        error = HttpStatus.BAD_REQUEST.name,
        message = "Erro: $erroFormat",
        path = request.servletPath
    )
}
@ExceptionHandler(Exception::class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
fun erroInterno(exception: Exception, request: HttpServletRequest): ErroView {
    return ErroView(
        status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        error = HttpStatus.INTERNAL_SERVER_ERROR.name,
        message = "Erro interno.",
        path = request.servletPath
    )
}
@ExceptionHandler(HttpMessageNotReadableException::class) //404
@ResponseStatus(HttpStatus.BAD_REQUEST)
fun campoIncorreto(exception: HttpMessageNotReadableException, request: HttpServletRequest): ErroView {
    return ErroView(
        status = HttpStatus.BAD_REQUEST.value(),
        error = HttpStatus.BAD_REQUEST.name,
        message = "Algum campo está faltando ou campo preenchido incorretamente.",
        path = request.servletPath
    )
}
@ExceptionHandler(DataIntegrityViolationException::class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
fun naoPodeDeletar(exception: DataIntegrityViolationException, request: HttpServletRequest): ErroView{
    return ErroView(
        status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        error = HttpStatus.INTERNAL_SERVER_ERROR.name,
        message = "Algum campo não tem correspondencia ou esse produto não pode ser deletado pois outros produtos dependem dele.",
        path = request.servletPath
    )
}
@ExceptionHandler(HttpRequestMethodNotSupportedException::class)
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) //405
fun urlErrada(exception: HttpRequestMethodNotSupportedException, request: HttpServletRequest): ErroView {
    return ErroView(
        status = HttpStatus.METHOD_NOT_ALLOWED.value(),
        error = HttpStatus.METHOD_NOT_ALLOWED.name,
        message = "A url está incorreta para esse método.",
        path = request.servletPath
    )
}
@ExceptionHandler(AuthenticationException::class) //401
@ResponseStatus(HttpStatus.UNAUTHORIZED)
fun naoAutorizado(exception: AuthenticationException, request: HttpServletRequest): ErroView {
    return ErroView(
        status = HttpStatus.UNAUTHORIZED.value(),
        error = HttpStatus.UNAUTHORIZED.name,
        message = "Acesso negado. Você precisa estar autenticado.",
        path = request.servletPath
    )
}

