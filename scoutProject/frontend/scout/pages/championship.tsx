import { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import apiCliente from '../api/apiClient';

interface ChampionshipType {
    championshipId: number;
    championshipName: string;
}

function Championship() {
    const [Championship, setChampionship] = useState<ChampionshipType[]>([]);

    useEffect(() => {
        apiCliente.get('/championship')
            .then(response => {
                setChampionship(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar dados: ', error);
            });
    }, []);

    return (
        <View>
            {Championship.map(champ => (
                <Text key={champ.championshipId}>{champ.championshipName}</Text>
            ))}
        </View>
    );
}

const styles = StyleSheet.create({
    
});

export default Championship;
