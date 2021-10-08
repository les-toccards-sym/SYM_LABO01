# SYM - Laboratoire 1

> Auteurs: Melvin Merk, Yohann Paulus & Doran Kayoumi

## Premières constatations

### Langue de l'interface

// TODO Doran

### Champs textuels de saisie
Pour l'addresse mail nous avons rajouté dans auth_fields.xml les lignes ```android:inputType="textEmailAddress"``` pour la saisie du mail et ```android:inputType="textPassword"``` pour la saisie du mot de passe.
### Mode paysage 
Pour le mode paysage, on a crée un nouveau dossier ```layout-land``` qui contient également un fichier activity_main.xml. Celui-ci permet de définir le comportement de l'application lorsque le téléphone est orienté en mode paysage.


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
Pour commencer nous avons commencé par crée le fichier ```ProfileActivity.kt```et le fichier xml correspondant ```activity_profile.xml```. Le premier fichier contient le code kotlin qui redéfini la fonction "onCreate()" qui rédige le comportement de l'application lors de la création de l'activitée. Le se fichier xml qui permet de définir le layout. L'activité est lancée depuis ```MainActivity.kt``` avec la fonction ```startActivity()```qui prend en paramètre un ```Intent```. L'Intent permet de décrir abstraitement l'opération à effectué et les paramètres que cette opération nécessite (voir point suivant).

### Passage de paramètres à la nouvelle activité
Comme dit précédemment, l'Intent permet de passer des paramètres. Dans notre cas, c'est pour le lancement d'une nouvelle activité avec comme paramètre l'addresse mail de l'utilisateur qui vient de se connecter. Pour ce faire on utilise la méthode ```putExtra()``` qui permet de lier un identifiant avec une variable existante dans l'activité "principale". Pour récupéré la valeur dans la seconde activité, on utilise l'appel ```intent.getStringExtra()``` qui prend un identifiant en paramètre et on stocke le résultat dans une variable.

### Permissions simples
Afin d'autorisé l'application à accédé à internet, nous avons du rajouter la ligne ```<uses-permission android:name="android.permission.INTERNET"/>``` dans le fichier ```AndroidManifest.xml```. 


## Navigation entre les activités

### Création et lancement de la nouvelle activité

TODO Doran

### Affichage d'une image

### Factorisation du code

TODO Doran

### Cycle de vie
