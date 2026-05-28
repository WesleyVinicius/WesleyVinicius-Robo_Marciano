import java.util.Scanner

// 1. Interface para o comando personalizado da versão premium
interface AcaoPersonalizada {
    fun executar()
}

// 2. Classe Base: Robô Marciano
open class RoboMarciano {
    open fun responda(frase: String): String {
        val texto = frase.trim()
        
        if (texto.isEmpty()) return "Não me incomode"

        val isPergunta = texto.endsWith("?")
        val temGrito = texto.split(Regex("\\W+")).any {
            it.length >= 2 && it == it.uppercase() && it.matches(Regex(".*[A-ZÁÉÍÓÚÂÊÎÔÛÃÕÇ].*"))
        }
        val temEu = texto.contains(Regex("\\beu\\b", RegexOption.IGNORE_CASE))

        if (isPergunta && temGrito) return "Relaxa, eu sei o que estou fazendo!" 
        if (temGrito) return "Opa! Calma aí!" 
        if (isPergunta) return "Certamente" 
        if (temEu) return "A responsabilidade é sua" 

        return "Tudo bem, como quiser"
    }
}

// 3. Classe de Extensão: Robô Avançado (Operações Matemáticas)
open class RoboMarcianoAvancado : RoboMarciano() {
    fun responda(operacao: String, a: Double, b: Double): String {
        val resultado = when (operacao.lowercase()) {
            "some" -> a + b
            "subtraia" -> a - b
            "multiplique" -> a * b
            "divida" -> if (b != 0.0) a / b else return "Essa eu sei, mas não posso dividir por zero!"
            else -> return "Essa operação eu não conheço."
        }
        
        val formatoFinal = if (resultado % 1 == 0.0) resultado.toInt() else resultado
        return "Essa eu sei, $formatoFinal"
    }
}

// 4. Classe Premium: Ações Personalizadas
class RoboMarcianoPremium(private val acao: AcaoPersonalizada) : RoboMarcianoAvancado() {
    override fun responda(frase: String): String {
        if (frase.contains(Regex("\\bagir\\b", RegexOption.IGNORE_CASE))) {
            acao.executar()
            return "É pra já!"
        }
        return super.responda(frase)
    }
}

// 5. Função Principal (Pronta para o VS Code / OnlineGDB)
fun main() {
    // Alteração: Ação personalizada focada no exemplo simples (Fazer Café)
    val acaoCafe = object : AcaoPersonalizada {
        override fun executar() {
            println("[SISTEMA]: Moendo grãos e preparando o seu café...")
        }
    }

    val robo = RoboMarcianoPremium(acaoCafe)
    val scanner = Scanner(System.`in`)

    println("ROBÔ MARCIANO PREMIUM LIGADO (MODO INTERATIVO)")

    while (true) {
        print("\nVocê: ")
        
        // Lê o que você digitar no teclado
        val entrada = scanner.nextLine() ?: ""

        if (entrada.uppercase() == "FIM") {
            println("Marciano: Encerrando os sistemas. Até mais!")
            break
        }

        val partes = entrada.trim().split(Regex("\\s+"))
        val operacoesReconhecidas = listOf("some", "subtraia", "multiplique", "divida")

        if (partes.size == 3 && operacoesReconhecidas.contains(partes[0].lowercase())) {
            try {
                val a = partes[1].toDouble()
                val b = partes[2].toDouble()
                println("Marciano: ${robo.responda(partes[0], a, b)}")
                continue 
            } catch (e: NumberFormatException) {
                // Ignora se não forem números e segue para o processamento de texto
            }
        }

        println("Marciano: ${robo.responda(entrada)}")
    }
}