# Mega Man - Simulação de um jogo (P3)

Simulação em console do Mega Man desenvolvida em Java 17. O projeto demonstra quatro padrões de projeto: **State**, **Strategy**, **Abstract Factory** e **Singleton**.

## Padrões de projeto

| Padrão | Classificação do trabalho | Classes principais | Como funciona no projeto |
|---|---|---|---|
| **State** | Classe A | `EstadoMegaMan`, `EstadoBase`, `Parado`, `Andando`, `Pulando`, `Deslizando`, `Machucado`, `Morto` | O personagem delega suas ações ao estado atual. Cada estado permite ou bloqueia ações e controla as transições. Trocar arma, curar e reviver também são decisões do estado. |
| **Strategy** | Classe A | `Arma`, `ArmaBase`, `MegaBuster`, `MetalBlade`, `LeafShield`, `CrashBomber`, `AtomicFire` | O personagem usa a interface `Arma` e pode trocar o algoritmo durante a execução. As armas têm comportamentos concretos diferentes, não apenas valores distintos. |
| **Abstract Factory** | Classe A | `FaseFactory`, `WoodManFaseFactory`, `AirManFaseFactory`, `FaseFactoryProvider` | Cada fábrica cria uma família de objetos de uma fase: inimigo comum, chefe e arma bônus. O menu trabalha com a interface, sem conhecer a implementação concreta da fábrica. |
| **Singleton** | Classe B | `Rush` | `getInstance()` fornece sempre a mesma instância. Invocar e dispensar alteram o estado interno do Rush; não destroem nem recriam o objeto. |

**Classificação:** o projeto contém três padrões de Classe A e um de Classe B. Assim, atende ao requisito de pelo menos dois padrões de Classe A e mais dois padrões das classes A ou B.

## Como os padrões foram implementados

### State

`MegaMan` mantém uma referência para `EstadoMegaMan` e delega as ações. `Parado`, `Andando` e `Pulando` compartilham o comportamento de ataque implementado em `EstadoBase`. `Deslizando`, `Machucado` e `Morto` sobrescrevem ações que não podem executar.

A interface também recebe `mudarArma`, `curar` e `reviver`. Os estados vivos permitem troca de arma e cura; apenas `Morto` executa a restauração do checkpoint. Assim, as decisões de disponibilidade não ficam espalhadas por condicionais no contexto.

### Strategy e Template Method

A interface `Arma` representa a estratégia de ataque. `ArmaBase.atacar()` é um Template Method: fixa a sequência de validação de alvos, verificação de energia, execução do ataque, aplicação dos efeitos e finalização. Os hooks `selecionarAlvos`, `calcularDano`, `podeExecutarAtaque`, `aplicarEfeito` e `aposAtaque` permitem variações reais.

- **Mega Buster:** tiro básico contra o primeiro inimigo vivo.
- **Metal Blade:** lança lâminas em várias direções e atinge todos os inimigos vivos.
- **Leaf Shield:** ataca um alvo ou ativa uma defesa que absorve o próximo dano recebido.
- **Crash Bomber:** causa dano ao alvo principal e dano colateral aos demais inimigos da área.
- **Atomic Fire:** precisa de pelo menos dois turnos de carga; a carga pode chegar a três níveis e aumenta o dano. A opção de carregar avança o turno e permite que os inimigos reajam enquanto Mega Man prepara o disparo.

### Abstract Factory

`FaseFactory` define três produtos relacionados: inimigo comum, chefe e arma bônus. `WoodManFaseFactory` e `AirManFaseFactory` implementam famílias diferentes. Ao escolher outra fase, `Main` troca apenas a fábrica concreta usada.

### Singleton

`Rush` tem construtor privado e acesso por `getInstance()`. Seu estado interno inclui `naTela`, usos restantes do Coil e cooldown. `dispensar()` apenas altera `naTela`, mantendo a mesma instância.

## Estrutura do projeto

```text
src/megaman/
├── Main.java
├── core/
│   └── MegaMan.java
├── model/
│   ├── Inimigo.java
│   ├── TipoArma.java
│   ├── TipoInimigo.java
│   └── TipoFase.java
├── state/
│   ├── EstadoMegaMan.java
│   ├── EstadoBase.java
│   └── Parado, Andando, Pulando, Deslizando, Machucado, Morto
├── strategy/
│   ├── Arma.java
│   ├── ArmaBase.java
│   └── MegaBuster, MetalBlade, LeafShield, CrashBomber, AtomicFire
├── factory/
│   ├── FaseFactory.java
│   ├── FaseFactoryProvider.java
│   ├── WoodManFaseFactory.java
│   └── AirManFaseFactory.java
└── singleton/
    └── Rush.java
```

Os tipos de domínio (`Inimigo` e os enums) ficam em `model`. Assim, `strategy` e `factory` dependem de abstrações/modelos, sem importar `MegaMan` ou depender de `core`; o próprio `Main` coordena o turno dos inimigos.

## Como executar

No terminal, a partir da pasta do projeto:

**Windows (PowerShell):**

```powershell
New-Item -ItemType Directory -Force out
$sources = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $sources
java -cp out megaman.Main
```

**Linux/macOS:**

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out megaman.Main
```

Também é possível abrir o projeto no IntelliJ IDEA e executar `megaman.Main` (o projeto está configurado para Java 17).

## Fluxo sugerido para demonstrar

1. Escolha uma fase e gere inimigos comuns e o chefe.
2. Equipe Metal Blade e ataque mais de um inimigo para demonstrar o ataque em várias direções.
3. Equipe Crash Bomber e observe o dano em área.
4. Equipe Atomic Fire, carregue por dois ou três turnos e então ataque.
5. Equipe Leaf Shield, ative a defesa e aguarde o turno dos inimigos para demonstrar o bloqueio.
6. Derrote o chefe e receba a arma bônus da fábrica da fase.
7. Invoque Rush, use o Coil, aguarde o cooldown e dispense Rush. Ao invocá-lo novamente, a mesma instância continua sendo utilizada.
8. Receba dano para observar o State `Machucado`; reduza a vida a zero para demonstrar que somente `Morto` permite reviver.

## Observação sobre a simulação

O jogo é uma simulação didática por turnos e console. Ao escolher **Aguardar turno**, os estados temporários avançam e todos os inimigos vivos realizam seus ataques. O personagem fica invulnerável durante o estado `Machucado`, conforme as regras simplificadas deste projeto.
