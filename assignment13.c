#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *data;
    size_t length;
    size_t capacity;
} StringBuffer;

/* Initialize StringBuffer */
StringBuffer* sb_init(size_t initial_capacity) {

    StringBuffer *sb =
        (StringBuffer*)malloc(sizeof(StringBuffer));

    if (sb == NULL) {
        printf("Structure allocation failed.\n");
        return NULL;
    }

    sb->data =
        (char*)malloc(initial_capacity * sizeof(char));

    if (sb->data == NULL) {
        printf("Buffer allocation failed.\n");
        free(sb);
        return NULL;
    }

    sb->length = 0;
    sb->capacity = initial_capacity;

    sb->data[0] = '\0';

    return sb;
}

/* Append string to buffer */
void sb_append(StringBuffer *sb, const char *str) {

    size_t str_len = strlen(str);

    /* Resize until enough space exists */
    while (sb->length + str_len + 1 > sb->capacity) {

        size_t new_capacity = sb->capacity * 2;

        char *temp =
            (char*)realloc(sb->data, new_capacity);

        if (temp == NULL) {
            printf("Reallocation failed.\n");
            return;
        }

        sb->data = temp;
        sb->capacity = new_capacity;

        printf("Buffer resized to: %zu\n",sb->capacity);
    }

    /* Append new string */
    strcpy(sb->data + sb->length, str);

    sb->length += str_len;
}

/* Free memory */
void sb_free(StringBuffer *sb) {

    free(sb->data);
    free(sb);
}

int main() {

    char input[100];

    /* Small initial capacity */
    StringBuffer *sb = sb_init(8);

    if (sb == NULL) {
        return 1;
    }

    printf("Initial Capacity: %zu\n\n",sb->capacity);

    while (1) {

        printf("Enter text (type EXIT to stop): ");
        fgets(input, sizeof(input), stdin);

        /* Remove newline */
        input[strcspn(input, "\n")] = '\0';

        /* Stop condition */
        if (strcmp(input, "EXIT") == 0) {
            break;
        }

        /* Append user input */
        sb_append(sb, input);

        printf("\nCurrent String: %s\n",sb->data);

        printf("Length: %zu\n",sb->length);

        printf("Capacity: %zu\n\n",sb->capacity);
    }

    printf("\nFinal String:\n%s\n", sb->data);

    sb_free(sb);

    printf("\nMemory freed successfully.\n");

    return 0;
}