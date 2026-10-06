# CLAUDE.md

Instruções para agentes de IA que trabalham neste repositório (Claude e qualquer outro modelo/ferramenta).

## Regra fundamental — autoria do código

> **Não deve ser escrito nada de código dentro do projeto.** O código-fonte do projeto será tocado 100% apenas pelo dono do projeto. O mais longe que um agente de IA irá ao escrever "código" será **pseudo-código**, usado como exemplo de designs e de funções específicas, para que o dono entenda como as coisas devem ser usadas em certos conceitos.

## O que isso significa na prática

**Proibido (nunca fazer):**

- Editar, criar, sobrescrever ou refatorar qualquer arquivo de código-fonte do projeto (`.java`, `.kt`, `.kts`, `.gradle`, `.properties`, `.sql`, `.nix`, scripts, configurações de build etc.).
- Aplicar patches, "correções" de código ou rodar comandos que alterem o código-fonte do projeto.
- Escrever código real que deva ser colado ou commitado no repositório.
- **Executar comandos de build** (`./gradlew`, `gradle`, `mvn`, etc.), incluindo compilar, testar, empacotar ou rodar o projeto.
- **Executar comandos de git** (`commit`, `push`, `pull`, `merge`, `rebase`, `checkout`, `branch`, etc.). Toda operação no repositório local ou remoto é feita apenas pelo dono do projeto.

**Permitido (pode fazer):**

- Explicar conceitos, propor designs, sugerir arquiteturas e responder dúvidas.
- Escrever **pseudo-código** (em texto ou em documentação) como exemplo ilustrativo, deixando claro que não é código para ser copiado para o projeto.
- Analisar, avaliar, revisar e apontar problemas no código existente, sem alterá-lo.
- **Sugerir** comandos de build/git, **mensagens de commit** e outras ações, explicando o que cada um faz — sem executá-los.

## Como responder sobre build, git e outras ferramentas

Quando for necessário usar build, git ou ferramentas semelhantes, o agente deve:

1. **Explicar o problema** de forma clara.
2. **Dar o(s) exemplo(s) de comando** para o dono rodar por conta própria.
3. **Explicar o efeito** de cada comando no repositório local e no repositório remoto (GitHub).

Exemplo:

> "Duas branches completamente diferentes que deveriam ser parecidas? Com a `branch1` em checkout, use `git merge branch2`."
>
> O comando junta o histórico da `branch2` na `branch1`. **Local:** cria um commit de merge no seu repositório local, unindo as duas linhas de histórico (e pode gerar conflitos que você resolve manualmente). **GitHub:** nada muda ainda — o merge só aparece lá depois que você fizer `git push` da branch resultante.

> Em caso de dúvida: apenas explicar e sugerir. Nunca escrever código real, nem executar comandos de build ou git dentro do repositório.
