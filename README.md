# SYM - Laboratoire 1

> Auteurs: Melvin Merk, Yohann Paulus & Doran Kayoumi

## Premières constatations

### Langue de l'interface

// TODO Doran

### Champs textuels de saisie

### Mode paysage



## Gestion des événements et mise à jour de l'interface

### Vérification du format de l'email

Pour la validation du format de l'email, nous avons utilisé une expression régulière.

```kotlin
fun validateEmail(email: String): Boolean {
  val regex = """^[a-zA-Z0-9_]+(?:.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$""".toRegex()
  return regex.matches(email)
}
```

Une autre solution serait d'utiliser le package `android.util.Patterns` qui fournit, entre autres, un pattern pour les adresses email.

```kotlin
fun validateEmail(email: String): Boolean {
  return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
}
```

### Verification du couple email/mot de passe

Pour vérifier si le couple email et mot de passe renseigné par l'utilisateur, nous effectuons une recherche de ce couple dans la liste des credentials.

> Remarques: Nous avons changé la liste donnée en une liste mutable. Ceci pour nous permettre d'ajouter des nouveaux credentials sans devoir recréer la liste.

```kotlin
credentials.any {
  it == Pair(email, password)
}
```

Dans un cas réel (i.e. non-académique), il faudrait que les mots de passe stockés dans la base de données (ici en mémoire) soient hashé  (avec un algorithme tel que `argon2`) et donc avant de pouvoir effectuer la chercher, le mot de passe saisie doit être hashé.



En cas d'échec, nous affichons une dialogue avec un message d'erreur.

```kotlin
AlertDialog.Builder(this).apply {
  setTitle("Failed authentication")
  setMessage("Your login details are incorrect")
  setPositiveButton("Ok") { dialog, _ ->
    dialog.dismiss()
  }.create().show()
}
```

## Passage à une autre activité

### Création et lancement de la nouvelle activité

### Passage de paramètres à la nouvelle activité

### Permissions simples



## Navigation entre les activités

### Création et lancement de la nouvelle activité

TODO Doran

### Affichage d'une image

### Factorisation du code

TODO Doran

### Cycle de vie
